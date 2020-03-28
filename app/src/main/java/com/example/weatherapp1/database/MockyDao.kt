package com.example.weatherapp1.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MockyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: List<MockyEntity>)

    @Query("select * from mockyentity")
    fun getEntities(): LiveData<List<MockyEntity>>
}