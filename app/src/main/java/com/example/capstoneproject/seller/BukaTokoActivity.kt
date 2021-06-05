package com.example.capstoneproject.seller

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.R
import kotlinx.android.synthetic.main.bukatoko.*

class BukaTokoActivity  : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bukatoko)

        btn_lanjut.setOnClickListener {
            val namaToko = nama_toko.text.toString()
            val url = url.text.toString()
            val alamat = alamat.text.toString()

            if (namaToko.isEmpty()|| url.isEmpty()|| alamat.isEmpty()) {
                Toast.makeText(this, "Isi dengan lengkap", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(namaToko.isNotEmpty()|| url.isNotEmpty()|| alamat.isNotEmpty()) {

                val intent = Intent (this,ProfileTokoActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}