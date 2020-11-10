package com.example.my_pattern_in_android.common

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * This class is common for all activity
 */
open class BaseActivity : AppCompatActivity() {


    protected fun showToast(message: Any, isLong: Boolean = false) {

        val msg = message.toString();

        val duration: Int = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT

        Toast.makeText(applicationContext, msg, duration).show()
    }
}