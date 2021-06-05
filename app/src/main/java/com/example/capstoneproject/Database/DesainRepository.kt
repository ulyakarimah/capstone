package com.example.capstoneproject.Database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.capstoneproject.Desain
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class DesainRepository(application: Application) {
    private val mDesainDao : DesainDAO
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = DesainRoomDatabase.getDatabase(application)
        mDesainDao = db.desainDao()
    }
    fun getAllDesigns(): LiveData<List<Desain>> = mDesainDao.getAllDesigns()
    fun insert(desain: Desain) {
        executorService.execute { mDesainDao.insert(desain) }
    }
    fun delete(desain: Desain) {
        executorService.execute { mDesainDao.delete(desain) }
    }
    fun update(desain: Desain) {
        executorService.execute { mDesainDao.update(desain) }
    }
}