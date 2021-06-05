package com.example.capstoneproject.seller

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.R
import kotlinx.android.synthetic.main.profile_toko.*

class ProfileTokoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_toko)

        btn_tambahkan.setOnClickListener {
            val intent = Intent(this, InputProdukActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}