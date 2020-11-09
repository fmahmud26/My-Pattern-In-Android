package com.example.my_pattern_in_android.features.main_ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_pattern_in_android.features.main_ui.model.PictureRepository
import com.example.my_pattern_in_android.features.main_ui.model.data_class.MyPictures
import com.example.my_pattern_in_android.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class PictureViewModel @ViewModelInject constructor(private val repository: PictureRepository) :
    ViewModel() {

    val picsData: MutableLiveData<Resource<List<MyPictures>>> = MutableLiveData()


    init {
        getPictures()
    }

    private fun getPictures() {
        viewModelScope.launch {
            fetchPictures()
        }
    }

    private suspend fun fetchPictures() {

        picsData.postValue(Resource.Loading())

        try {


            val response = repository.getUsers()

            picsData.postValue(handlePicsResponse(response))


        } catch (t: Throwable) {

            println("Network Error ${t.localizedMessage}")

            when (t) {
                is IOException -> picsData.postValue(Resource.Error("Network failure"))
                else -> picsData.postValue(Resource.Error("Some error occurred"))
            }
        }
    }

    private fun getValue(): Boolean {
        return true
    }


    private fun handlePicsResponse(response: Response<List<MyPictures>>): Resource<List<MyPictures>>? {

        if (response.isSuccessful) {

            response.body()?.let { picResponse ->

                return Resource.Success(picResponse)
            }
        }

        return Resource.Error(response.message())

    }

}