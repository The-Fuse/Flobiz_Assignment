package com.rohit.flobizassignment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rohit.flobizassignment.database.LocalDatabase
import com.rohit.flobizassignment.databinding.ActivityMainBinding
import com.rohit.flobizassignment.main.MainViewModel
import com.rohit.flobizassignment.main.MainViewModelFactory
import com.rohit.flobizassignment.main.RecyclerAdapter

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityMainBinding.inflate(layoutInflater)
        val dataSource = LocalDatabase.getInstance(application).databaseDao
        val viewModelFactory = MainViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
        binding.lifecycleOwner = this

        val adapter = RecyclerAdapter()
        binding.recyclerView.adapter =adapter
        viewModel.localData.observe(this, Observer {
            it.let {
                Log.d(TAG, "onCreate: ${it.size}")
                adapter.submitList(it)
            }
        })
        adapter.submitList(viewModel.localData.value)

        binding.imageView2.setOnClickListener {
            closeAdView()
        }
        setContentView(binding.root)
    }

    private fun closeAdView() {
        val sharedPref = this.getSharedPreferences("Flobiz", Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean("Visibility", true).apply()
        binding.constraintLayout.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = this.getSharedPreferences("Flobiz", Context.MODE_PRIVATE)
        val pref = sharedPref.getBoolean("Visibility",false)
        if (pref){
            binding.constraintLayout.visibility = View.GONE
        }
    }
}