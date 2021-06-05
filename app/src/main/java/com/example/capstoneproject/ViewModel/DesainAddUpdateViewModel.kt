package com.example.capstoneproject.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.capstoneproject.Desain
import com.example.capstoneproject.Database.DesainRepository

class DesainAddUpdateViewModel (application: Application) : ViewModel(){

    private val mDesainRepository: DesainRepository = DesainRepository(application)
    fun insert(desain: Desain) {
        mDesainRepository.insert(desain)
    }
    fun update(desain: Desain) {
        mDesainRepository.update(desain)
    }
    fun delete(desain: Desain) {
        mDesainRepository.delete(desain)
    }

}