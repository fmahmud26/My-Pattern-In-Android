package com.example.my_pattern_in_android.features.main_ui.model

import com.example.my_pattern_in_android.network.MyApi
import javax.inject.Inject


class PictureRepository @Inject constructor(private val api: MyApi) {

    suspend fun getUsers() = api.getPictures()

}