package com.example.capstoneproject.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.Desain
import com.example.capstoneproject.Database.DesainRepository

class MainViewModel(application: Application) : ViewModel() {

    private val mDesainRepository: DesainRepository = DesainRepository(application)
    fun getAllDesigns(): LiveData<List<Desain>> = mDesainRepository.getAllDesigns()

}