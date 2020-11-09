package com.example.my_pattern_in_android.common

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {


    protected fun showToast(message: Any, isLong: Boolean = false) {

        val message = message.toString();

        val duration: Int = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT

        Toast.makeText(context, message, duration).show()
    }
}