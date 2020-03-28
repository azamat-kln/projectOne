package com.example.weatherapp1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MockyEntity::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract val roomDao: MockyDao
}

private lateinit var INSTANCE: MyDatabase

fun getDatabase(context: Context): MyDatabase {
    synchronized(MyDatabase::class.java) {

        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java,
                "my_db"
            ).fallbackToDestructiveMigration().build()
        }

    }
    return INSTANCE
}
