package com.example.my_pattern_in_android.ui.main_ui.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.my_pattern_in_android.R
import com.example.my_pattern_in_android.common.BaseActivity
import com.example.my_pattern_in_android.ui.main_ui.model.data_class.MyPictures
import com.example.my_pattern_in_android.ui.main_ui.viewmodel.PictureViewModel
import com.example.my_pattern_in_android.common.Resources
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var preferences: SharedPreferences

    private val viewModel: PictureViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setLiveDataListener()

    }

    private fun setLiveDataListener() {

        viewModel.picLiveData.observe(this, Observer { response ->
            when (response) {

                is Resources.Success -> {
                    hideLoader()
                    updateUI(response.data)
                }

                is Resources.Error -> {
                    hideLoader()
                    response.message?.let { showToast(it) }
                }

                is Resources.Loading -> {
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