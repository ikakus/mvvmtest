package com.mvvmexample.ikakus.monrocketlist.feature.rocketdetails.di

import com.mvvmexample.ikakus.data.datasource.rocket.LocalRocketDataSource
import com.mvvmexample.ikakus.data.datasource.rocket.RemoteRocketDataSource
import com.mvvmexample.ikakus.data.repository.RocketRepository
import com.mvvmexample.ikakus.monrocketlist.feature.rocketdetails.RocketDetailsViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

class RocketDetailsModule {
  val instance = applicationContext {
    factory {
      RocketRepository(
          get(),
          RemoteRocketDataSource(get()),
          LocalRocketDataSource(get())
      )
    }
    viewModel { RocketDetailsViewModel(get()) }
  }
}