package com.example.capstoneproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.capstoneproject.R
import com.example.capstoneproject.databinding.ActivityMainBinding

// Android 1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.desainku.setOnClickListener {
            startActivity(Intent(this, DesainkuActivity::class.java)) }

        binding.konveksiTerdekat.setOnClickListener {
            startActivity(Intent(this, KonveksiActivity::class.java)) }
    }
}
