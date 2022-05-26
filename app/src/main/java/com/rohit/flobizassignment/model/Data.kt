package com.rohit.flobizassignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class Data(

    @PrimaryKey
    val id: String,
    val name: String,
    val imageLink: String
)
