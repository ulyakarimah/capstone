package com.example.capstoneproject.Database
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstoneproject.Desain

@Dao
interface DesainDAO {
    @Insert
    fun insert(desain: Desain)
    @Update
    fun update(desain: Desain)
    @Delete
    fun delete(desain: Desain)
    @Query("SELECT * from desain ORDER BY id ASC")
    fun getAllDesigns(): LiveData<List<Desain>>
}

//onConflict = OnConflictStrategy.IGNORE