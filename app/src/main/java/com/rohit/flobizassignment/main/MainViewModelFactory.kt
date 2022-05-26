package com.rohit.flobizassignment.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rohit.flobizassignment.database.DatabaseDao
import com.rohit.flobizassignment.database.LocalDatabase

class MainViewModelFactory(private val dataSource: DatabaseDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataSource) as T
        }
        throw java.lang.IllegalArgumentException("Unknown View Model")
    }

}