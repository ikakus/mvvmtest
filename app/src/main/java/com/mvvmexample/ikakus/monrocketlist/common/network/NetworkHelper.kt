package com.mvvmexample.ikakus.monrocketlist.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.mvvmexample.ikakus.data.NetworkConnectionStateProvider

class NetworkHelper(val context: Context): NetworkConnectionStateProvider {
  override fun isConnected(): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
  }
}