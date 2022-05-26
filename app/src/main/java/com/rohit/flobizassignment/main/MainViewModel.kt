package com.rohit.flobizassignment.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohit.flobizassignment.database.DatabaseDao
import com.rohit.flobizassignment.model.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainViewModel"

class MainViewModel(private val dataSource: DatabaseDao) : ViewModel() {

    val data = listOf<Data>(
        Data(
            "1",
            "Technology is very interesting.",
            "https://image.shutterstock.com/image-illustration/abstract-futuristic-infographic-visual-data-260nw-746652745.jpg"
        ),
        Data(
            "2",
            "Big Data is a new field for developers",
            "https://miro.medium.com/max/1024/0*QxsWlMTDGmTebavF.jpg"
        ),
        Data(
            "3",
            "Secured Encryption is necessary",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS__W6OLuocXF5l1T6fqQI6sJkIVcfGB9trIw&usqp=CAU"
        ),
        Data(
            "4",
            "Data is very important for ML",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJ7_g1fX7AH7myhq9La9IwiMzLRm6H-3zw_Q&usqp=CAU"
        ),
        Data(
            "5",
            "Be prepared for everything",
            "https://media.istockphoto.com/photos/mountain-landscape-picture-id517188688?k=20&m=517188688&s=612x612&w=0&h=i38qBm2P-6V4vZVEaMy_TaTEaoCMkYhvLCysE7yJQ5Q="
        ),
        Data(
            "6",
            "Sun is looking very amazing",
            "https://images.pexels.com/photos/1172253/pexels-photo-1172253.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        ),
        Data(
            "7",
            "Tourism is the main source of income for some countries",
            "https://d3nn873nee648n.cloudfront.net/HomeImages/Concept-and-Ideas.jpg"
        ),
        Data(
            "8",
            "Art of pictures",
            "https://www.whatsappimages.in/wp-content/uploads/2022/04/DP-Pics-Images-Wallpaper-for-Whatsapp.jpg"
        ),
        Data(
            "9",
            "Winter is very good season for indians",
            "https://images.pexels.com/photos/302743/pexels-photo-302743.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        ),
        Data(
            "10",
            "Volvo Car Images",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-MNxTKwV0dK5HP14KV0Muh6piqPGTv8w4PQ&usqp=CAU"
        ),
        Data(
            "11",
            "Study self help books for better life",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTqkdTATUpuiK3xcLpa0Okfc6wzJXZTr1ZvRQ&usqp=CAU"
        ),
        Data(
            "12",
            "Winter is very good season",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStKbI0JYZFAXrGNHlamCl4dnYSmopYS1bBfw&usqp=CAU"
        ),
        Data(
            "13",
            "Bridges are a very good source",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3Ne4fxlOxhGDTycjkVZd_4KxtraQ0WP4DoQ&usqp=CAU"
        ),
        Data(
            "14",
            "Asian chinese female",
            "https://media.istockphoto.com/photos/asian-chinese-mid-adult-female-astronaut-looking-at-earth-through-picture-id1332953647?b=1&k=20&m=1332953647&s=170667a&w=0&h=gxISYTdEoy-pmIiJAR0OnIIltLg9UNxQQCpAFoEKuaw="
        ),
        Data(
            "15",
            "Apple to launch new soon",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5_f-3Npwnj40B6u8O8WmcX8swxRqUS8ncQg&usqp=CAU"
        ),
        Data(
            "16",
            "Technology is very interesting ds.",
            "https://image.shutterstock.com/image-illustration/abstract-futuristic-infographic-visual-data-260nw-746652745.jpg"
        ),
        Data(
            "17",
            "Big Data is a new field for data analyst",
            "https://miro.medium.com/max/1024/0*QxsWlMTDGmTebavF.jpg"
        ),
        Data(
            "18",
            "Secured Encryption is necessary for world",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS__W6OLuocXF5l1T6fqQI6sJkIVcfGB9trIw&usqp=CAU"
        ),
        Data(
            "19",
            "Data is very important for ML AI",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJ7_g1fX7AH7myhq9La9IwiMzLRm6H-3zw_Q&usqp=CAU"
        ),
        Data(
            "20",
            "Be prepared for everything at everytime",
            "https://media.istockphoto.com/photos/mountain-landscape-picture-id517188688?k=20&m=517188688&s=612x612&w=0&h=i38qBm2P-6V4vZVEaMy_TaTEaoCMkYhvLCysE7yJQ5Q="
        ),
        Data(
            "21",
            "Sunrise is looking very amazing",
            "https://images.pexels.com/photos/1172253/pexels-photo-1172253.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        ),
        Data(
            "22",
            "Tourism is the main source of income for countries",
            "https://d3nn873nee648n.cloudfront.net/HomeImages/Concept-and-Ideas.jpg"
        ),
        Data(
            "23",
            "Art drawing of pictures",
            "https://www.whatsappimages.in/wp-content/uploads/2022/04/DP-Pics-Images-Wallpaper-for-Whatsapp.jpg"
        ),
        Data(
            "24",
            "Winter is very good",
            "https://images.pexels.com/photos/302743/pexels-photo-302743.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        ),
        Data(
            "25",
            "Volvo Car Image",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-MNxTKwV0dK5HP14KV0Muh6piqPGTv8w4PQ&usqp=CAU"
        )
    )

    val localData = dataSource.getData()


    init {
        checkData()
    }

    private fun checkData() {
        localData.observeForever(Observer {
            if (it.isEmpty()) {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        dataSource.insertData(data)
                    }
                }
            }
        })


    }
}