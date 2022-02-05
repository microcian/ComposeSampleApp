package com.techvista.etisalat.commom.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    fun isNetworkAvailable(app: Context): Boolean {
        return (app
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo != null
    }
}