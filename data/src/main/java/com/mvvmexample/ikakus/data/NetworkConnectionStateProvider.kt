package com.mvvmexample.ikakus.data

interface NetworkConnectionStateProvider {
  fun isConnected(): Boolean
}