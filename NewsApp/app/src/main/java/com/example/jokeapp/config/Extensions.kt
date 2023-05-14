package com.example.jokeapp.config

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Jitendra on 14/05/23.
 **/
internal fun Context.isNetworkAvailable(): Boolean {
    val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connMgr.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}
