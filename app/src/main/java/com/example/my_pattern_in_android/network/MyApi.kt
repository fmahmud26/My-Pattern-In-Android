package com.example.my_pattern_in_android.network

import com.example.my_pattern_in_android.ui.main_ui.model.data_class.MyPictures
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET("list")
    suspend fun getPictures(): Response<List<MyPictures>>
}