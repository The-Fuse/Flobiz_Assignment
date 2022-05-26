package com.rohit.flobizassignment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rohit.flobizassignment.model.Data

@Dao
interface DatabaseDao {

    @Insert
    suspend fun insertData(data: List<Data>)

    @Query("SELECT * FROM data_table")
    fun getData(): LiveData<List<Data>>
}