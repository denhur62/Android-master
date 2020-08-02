package com.example.myapplication


import android.R
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.samples.flickr.api.Api
import com.bumptech.glide.samples.flickr.api.Photo
import com.bumptech.glide.util.ViewPreloadSizeProvider


/**
 * A fragment that shows cropped image thumbnails half the width of the screen in a scrolling list.
 */
class FlickrPhotoList : Fragment(), PhotoViewer {
    private var adapter: FlickrPhotoListAdapter? = null
    private var currentPhotos: List<Photo>? = null
    private var list: RecyclerView? = null
    private var fullRequest: GlideRequest<Drawable>? = null
    private var thumbRequest: GlideRequest<Drawable>? = null
    private var preloadSizeProvider: ViewPreloadSizeProvider<Photo>? = null
    private var layoutManager: LinearLayoutManager? = null
    fun onPhotosUpdated(photos: List<Photo>?) {
        currentPhotos = photos
        if (adapter != null) {
            adapter!!.setPhotos(currentPhotos)
        }
    }

    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View {
        val result: View = inflater.inflate(R.layout.flickr_photo_list, container, false)
        list = result.findViewById<View>(R.id.flickr_photo_list) as RecyclerView
        layoutManager = LinearLayoutManager(getActivity())
        list.setLayoutManager(layoutManager)
        adapter = FlickrPhotoListAdapter()
        list.setAdapter(adapter)
        preloadSizeProvider = ViewPreloadSizeProvider()
        val preloader: RecyclerViewPreloader<Photo> = RecyclerViewPreloader(
                GlideApp.with(this), adapter, preloadSizeProvider, PRELOAD_AHEAD_ITEMS)
        list.addOnScrollListener(preloader)
        list.setItemViewCacheSize(0)
        if (currentPhotos != null) {
            adapter!!.setPhotos(currentPhotos)
        }
        val glideRequests: GlideRequests = GlideApp.with(this)
        fullRequest = glideRequests
                .asDrawable()
                .centerCrop()
                .placeholder(ColorDrawable(Color.GRAY))
        thumbRequest = glideRequests
                .asDrawable()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .override(Api.SQUARE_THUMB_SIZE)
                .transition(withCrossFade())
        list.setRecyclerListener(object : RecyclerListener() {
            fun onViewRecycled(holder: RecyclerView.ViewHolder) {
                val vh = holder as PhotoTitleViewHolder
                glideRequests.clear(vh.imageView)
            }
        })
        if (savedInstanceState != null) {
            val index = savedInstanceState.getInt(STATE_POSITION_INDEX)
            val offset = savedInstanceState.getInt(STATE_POSITION_OFFSET)
            layoutManager.scrollToPositionWithOffset(index, offset)
        }
        return result
    }

    fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (list != null) {
            val index: Int = layoutManager.findFirstVisibleItemPosition()
            val topView: View = list.getChildAt(0)
            val offset = topView?.top ?: 0
            outState.putInt(STATE_POSITION_INDEX, index)
            outState.putInt(STATE_POSITION_OFFSET, offset)
        }
    }

    private inner class FlickrPhotoListAdapter : RecyclerView.Adapter<PhotoTitleViewHolder?>(), ListPreloader.PreloadModelProvider<Photo?> {
        private val inflater: LayoutInflater
        private var photos: List<Photo>? = emptyList<Photo>()
        fun setPhotos(photos: List<Photo>?) {
            this.photos = photos
            notifyDataSetChanged()
        }

        fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PhotoTitleViewHolder {
            val view: View = inflater.inflate(R.layout.flickr_photo_list_item, parent, false)
            val vh = PhotoTitleViewHolder(view)
            preloadSizeProvider.setView(vh.imageView)
            return vh
        }

        fun onBindViewHolder(holder: PhotoTitleViewHolder, position: Int) {
            val current: Photo = photos!![position]
            fullRequest.load(current)
                    .thumbnail(thumbRequest.load(current))
                    .into(holder.imageView)
            holder.imageView.setOnClickListener {
                val intent: Intent = FullscreenActivity.getIntent(getActivity(), current)
                startActivity(intent)
            }
            holder.titleView.setText(current.getTitle())
        }

        fun getItemId(i: Int): Long {
            return RecyclerView.NO_ID
        }

        val itemCount: Int
            get() = photos!!.size

        fun getPreloadItems(position: Int): List<Photo> {
            return photos!!.subList(position, position + 1)
        }

        fun getPreloadRequestBuilder(item: Photo?): RequestBuilder<Drawable> {
            return fullRequest.thumbnail(thumbRequest.load(item)).load(item)
        }

        init {
            inflater = LayoutInflater.from(getActivity())
        }
    }

    private class PhotoTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView
        private val imageView: ImageView

        init {
            imageView = itemView.findViewById<View>(R.id.photo_view) as ImageView
            titleView = itemView.findViewById<View>(R.id.title_view) as TextView
        }
    }

    companion object {
        private const val PRELOAD_AHEAD_ITEMS = 5
        private const val STATE_POSITION_INDEX = "state_position_index"
        private const val STATE_POSITION_OFFSET = "state_position_offset"
        fun newInstance(): FlickrPhotoList {
            return FlickrPhotoList()
        }
    }
}