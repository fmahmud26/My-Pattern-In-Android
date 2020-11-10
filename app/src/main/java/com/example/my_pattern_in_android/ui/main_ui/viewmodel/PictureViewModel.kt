package com.example.my_pattern_in_android.ui.main_ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_pattern_in_android.ui.main_ui.model.PictureRepository
import com.example.my_pattern_in_android.ui.main_ui.model.data_class.MyPictures
import com.example.my_pattern_in_android.common.Resources
import com.example.my_pattern_in_android.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class PictureViewModel @ViewModelInject constructor(
    private val repository: PictureRepository,
    private val utils: Utils,
    @Assisted private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    val picLiveData: MutableLiveData<Resources<List<MyPictures>>> = MutableLiveData()

    init {
        getPictures()
    }

    private fun getPictures() {
        viewModelScope.launch {
            fetchPictures()
        }
    }

    private suspend fun fetchPictures() {

        picLiveData.postValue(Resources.Loading())

        try {

            if (utils.hasInternetConnection()) {
                val response = repository.getUsers()
                picLiveData.postValue(handlePicsResponse(response))
            } else {
                picLiveData.postValue(Resources.Error("No internet connection"))
            }

        } catch (t: Throwable) {

            when (t) {
                is IOException -> picLiveData.postValue(Resources.Error("Network failure"))
                else -> picLiveData.postValue(Resources.Error("Some error occurred"))
            }
        }
    }


    private fun handlePicsResponse(response: Response<List<MyPictures>>): Resources<List<MyPictures>>? {

        if (response.isSuccessful) {

            response.body()?.let { picResponse ->

                return Resources.Success(picResponse)
            }
        }

        return Resources.Error(response.message())

    }

}