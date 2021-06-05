package com.example.capstoneproject.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.capstoneproject.Desain

@Database(entities = [Desain::class], version = 1)
abstract class DesainRoomDatabase : RoomDatabase() {
    abstract fun desainDao(): DesainDAO
    companion object {
        @Volatile
        private var INSTANCE: DesainRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): DesainRoomDatabase {
            if (INSTANCE == null) {
                synchronized(DesainRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DesainRoomDatabase::class.java, "desain_database")
                        .build()
                }
            }
            return INSTANCE as DesainRoomDatabase
        }
    }
}