package com.example.demo

/**
 * Created by chaipeng on 2024/5/16
 * Tips:
 */
class HelloJni {

    companion object {
        init {
            System.loadLibrary("helloNdk")
        }
    }

    external fun stringFromJNI(): String

    external fun getFfmpegVersion(): String

}