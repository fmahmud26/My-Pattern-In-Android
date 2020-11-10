package com.example.my_pattern_in_android.common

import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * This class is common for all fragment
 */
open class BaseFragment : Fragment() {


    protected fun showToast(message: Any, isLong: Boolean = false) {

        val msg = message.toString();

        val duration: Int = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT

        Toast.makeText(context, msg, duration).show()
    }
}