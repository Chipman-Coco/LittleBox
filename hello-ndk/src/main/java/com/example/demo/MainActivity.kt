package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
//        binding.sampleText.text = stringFromJNI()

        val jni = HelloJni()
        try {
            binding.sampleText.text = jni.getFfmpegVersion()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}