package com.example.my_pattern_in_android.ui.main_ui.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.my_pattern_in_android.R
import com.example.my_pattern_in_android.common.BaseFragment
import com.example.my_pattern_in_android.common.Resources
import com.example.my_pattern_in_android.ui.main_ui.viewmodel.PictureViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DemoFragment : BaseFragment() {

    /**
     * How to create viewmodel reference
     */
    private val viewModel: PictureViewModel by viewModels()


    /**
     * SharedPreferences reference
     */
    @Inject
    lateinit var preferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLiveDataListener()

    }

    private fun setLiveDataListener() {

        /**
         * This is how, we observe live data
         */

        viewModel.picLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {

                is Resources.Success -> {
                    // hideLoader()
                    //  updateUI(response.data)
                }

                is Resources.Error -> {
                    //  hideLoader()
                    response.message?.let { showToast(it) }
                }

                is Resources.Loading -> {
                    // showLoader()
                }
            }
        })

    }
}