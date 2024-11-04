package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    init {
        System.loadLibrary("native-lib")
    }

    private external fun stringFromJNI(className: String, methodName: String): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
//        binding.sampleText.text = stringFromJNI()

//        val jni = HelloJni()
//        try {
//            jni.callJavaField("com/example/demo/test/HelloCallback","name")
//            jni.callJavaMethod("com/example/demo/test/HelloCallback", "updateName")
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

        stringFromJNI("com/example/demo/test/HelloCallback", "updateName")
    }


}