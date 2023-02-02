package com.example.practicaldrcsystems.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

object ExtFuncs {
    fun Context.notifyUser(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }

    //for logs
    fun logD(tag: String = "PRACTICAL_DRCSYSTEMS", message: String) {
        Log.d(tag, message)
    }

    fun logE(tag: String = "PRACTICAL_DRCSYSTEMS", message: String) {
        Log.e(tag, message)
    }

    fun logI(tag: String = "PRACTICAL_DRCSYSTEMS", message: String = "") {
        Log.i(tag, message)
    }
}