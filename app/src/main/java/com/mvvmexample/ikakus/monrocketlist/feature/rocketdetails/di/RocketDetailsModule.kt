package com.mvvmexample.ikakus.monrocketlist.feature.rocketdetails.di

import com.mvvmexample.ikakus.data.LocalRocketDataSource
import com.mvvmexample.ikakus.data.RemoteRocketDataSource
import com.mvvmexample.ikakus.data.RocketRepository
import com.mvvmexample.ikakus.monrocketlist.feature.rocketdetails.RocketDetailsViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

class RocketDetailsModule {
  val instance = applicationContext {
    factory {
      RocketRepository(
          RemoteRocketDataSource(get()),
          LocalRocketDataSource()
      )
    }
    viewModel { RocketDetailsViewModel(get(), get()) }
  }
}