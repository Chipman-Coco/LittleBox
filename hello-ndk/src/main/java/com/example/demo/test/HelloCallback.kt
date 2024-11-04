package com.example.demo.test

import android.util.Log

class HelloCallback {

    var name: String = "HelloBack"

    fun updateName(name: String) {
        this.name = name
        Log.d("TAG", "updateName: $name")
    }
}