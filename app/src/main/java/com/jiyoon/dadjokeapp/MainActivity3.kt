package com.jiyoon.dadjokeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jiyoon.dadjokeapp.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    val TAG: String = "로그"
    lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MainActivity3 - onCreate() called")
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.proudButton.setOnClickListener {
            var intent = Intent(this,MainActivity4::class.java)
            startActivity(intent)
        }
    }
}