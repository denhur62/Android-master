package com.example.webviewmission1

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var loadRequested = false
    var isUrlInputOpen= false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webSettings = webview.settings
        webSettings.javaScriptEnabled=true
        webSettings.setSupportZoom(true)
        var up=AnimationUtils.loadAnimation(this,R.anim.upside)
        var down=AnimationUtils.loadAnimation(this,R.anim.downside)
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                if (loadRequested) {
                    urlLayout.startAnimation(up)
                    loadRequested = false
                }
            }
        }
        var upDownListener=upDownListner()
        up.setAnimationListener(upDownListener)
        down.setAnimationListener(upDownListener)
        searchButton.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                var urlStr = urlInput.text.toString().trim { it <= ' ' }
                if (!urlStr.startsWith("http://")) {
                    urlStr = "http://$urlStr"
                    urlInput.setText(urlStr)
                }
                loadRequested = true
                webview.loadUrl(urlStr)
            }
        })
        handleButton.setOnClickListener{handleButton.startAnimation(down)}


    }
    private fun showHandleButton() {
        handleButton.visibility = View.VISIBLE
        urlLayout.visibility = View.GONE
        isUrlInputOpen = false
    }

    private fun showUrlInput() {
        handleButton.visibility = View.GONE
        urlLayout.visibility = View.VISIBLE
        isUrlInputOpen = true
    }
    inner class upDownListner :Animation.AnimationListener{
        override fun onAnimationRepeat(p0: Animation?) {
        }

        override fun onAnimationEnd(p0: Animation?) {
            if (isUrlInputOpen) {
                showHandleButton()
            } else {
                showUrlInput()
            }
        }

        override fun onAnimationStart(p0: Animation?) {
        }
    }
}