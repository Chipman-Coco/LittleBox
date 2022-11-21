package com.chipman.serve.aidl

import android.os.Parcel
import android.os.Parcelable

class Message(
    var msg: String = "",
    var type: Int = 0
) : Parcelable {

    companion object CREATOR : Parcelable.Creator<Message> {
        override fun createFromParcel(parcel: Parcel): Message {
            return Message(parcel)
        }

        override fun newArray(size: Int): Array<Message> {
            return Array(size) { Message() }
        }
    }

    private constructor(inParcel: Parcel) : this() {
        readFromParcel(inParcel)
    }

    override fun writeToParcel(outParcel: Parcel, flags: Int) {
        outParcel.writeString(msg)
        outParcel.writeInt(type)
    }

    /*private*/ fun readFromParcel(inParcel: Parcel) {
        msg = inParcel.readString() ?: ""
        type = inParcel.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }
}