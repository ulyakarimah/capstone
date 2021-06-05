package com.example.capstoneproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.capstoneproject.R

// Android 1

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgButtonDesainku : ImageButton = findViewById(R.id.desainku)
        imgButtonDesainku.setOnClickListener(this)

        val imgButtonKonveksi : ImageButton = findViewById(R.id.konveksi_terdekat)
        imgButtonKonveksi.setOnClickListener(this)
    }

    override fun onClick(v: View?){
        when(v?.id){
            R.id.desainku -> {
                val I = Intent(this@MainActivity, DesainkuActivity :: class.java )
                startActivity(I)
            }
            R.id.konveksi_terdekat ->{
                val I = Intent(this@MainActivity, KonveksiActivity :: class.java )
                startActivity(I)
            }
        }
    }
}