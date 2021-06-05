package com.example.capstoneproject.seller

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.R
import kotlinx.android.synthetic.main.input_produk.*
import kotlinx.android.synthetic.main.profile_toko.*

class InputProdukActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.input_produk)

        btn_tambah.setOnClickListener {
            val namaDesain = nama_desain.text.toString()
            val deskripsi = deskripsi.text.toString()
            val harga = harga.text.toString()

            if (namaDesain.isEmpty()|| deskripsi.isEmpty()|| harga.isEmpty()) {
                Toast.makeText(this, "Isi dengan lengkap", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(namaDesain.isNotEmpty()|| deskripsi.isNotEmpty()|| harga.isNotEmpty()) {

                val intent = Intent (this,ProfileTokoActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}
