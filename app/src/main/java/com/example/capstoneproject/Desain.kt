package com.example.capstoneproject

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class Desain (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "judul")
    var judul: String? = null,

    @ColumnInfo(name = "gambar")
    var gambar: Int? = 0

) : Parcelable