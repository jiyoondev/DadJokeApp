package com.jiyoon.dadjokeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jiyoon.dadjokeapp.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.jokingButton.setOnClickListener {
            var intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }
    }
}