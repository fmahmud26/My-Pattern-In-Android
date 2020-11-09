package com.example.my_pattern_in_android.ui.main_ui.model.data_class

import com.google.gson.annotations.SerializedName

data class MyPictures(

    @SerializedName("author")
    val name: String,
    @SerializedName("download_url")
    val photo: String
)