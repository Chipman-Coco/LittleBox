package com.example.demo.jni

class HelloJni {

    companion object {
        init {
            System.loadLibrary("helloJni")
        }
    }

    external fun callJavaField(className: String, fieldName: String)

    external fun callJavaMethod(className: String, methodName: String): Boolean

}