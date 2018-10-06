package com.mvvmexample.ikakus.monrocketlist

import android.app.Application
import com.mvvmexample.ikakus.monrocketlist.di.NetworkModule
import com.mvvmexample.ikakus.monrocketlist.feature.rockets.di.RocketListModule
import org.koin.android.ext.android.startKoin

class App: Application() {
  override fun onCreate() {
    super.onCreate()
    setupDi()
  }

  private fun setupDi() {
    startKoin(this, listOf(
        NetworkModule().instance,
        RocketListModule().instance))

  }
}