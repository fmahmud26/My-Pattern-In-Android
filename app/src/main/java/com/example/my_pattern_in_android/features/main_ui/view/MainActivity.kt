package com.example.my_pattern_in_android.features.main_ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.my_pattern_in_android.R
import com.example.my_pattern_in_android.common.BaseActivity
import com.example.my_pattern_in_android.features.main_ui.model.data_class.MyPictures
import com.example.my_pattern_in_android.utils.Resource
import com.example.my_pattern_in_android.features.main_ui.viewmodel.PictureViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: PictureViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setLiveDataListener()

    }

    private fun setLiveDataListener() {

        viewModel.picsData.observe(this, Observer { response ->
            when (response) {

                is Resource.Success -> {
                    hideLoader()
                    updateUI(response.data)
                }

                is Resource.Error -> {
                    hideLoader()
                    response.message?.let { showToast(it) }
                }

                is Resource.Loading -> {
                    showLoader()
                }
            }
        })
    }


    private fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        progressBar.visibility = View.GONE
    }

    private fun updateUI(data: List<MyPictures>?) {

        rvPhoto.adapter = data?.let { PictureAdapter(it, this) }

    }


}