package com.example.example3

import android.os.Parcel
import android.os.Parcelable


class SimpleData : Parcelable{
    var number: Int =0
    var message: String? =null
    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeInt(number)
        p0.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }
    constructor(num : Int , mes : String?){
        number=num
        message=mes
    }
    constructor(src: Parcel){
        number=src.readInt()
        message=src.readString()
    }
    companion object{ @JvmField

       val CREATOR: Parcelable.Creator<SimpleData> = object: Parcelable.Creator<SimpleData>{
            override fun createFromParcel(p0: Parcel):SimpleData {
                return SimpleData(p0)
            }

            override fun newArray(p0: Int): Array<SimpleData?> {
               return arrayOfNulls(p0)
            }

        }
    }
}