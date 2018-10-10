package com.mvvmexample.ikakus.monrocketlist.feature.rockets.di

import com.mvvmexample.ikakus.data.datasource.rocket.LocalRocketDataSource
import com.mvvmexample.ikakus.data.datasource.rocket.RemoteRocketDataSource
import com.mvvmexample.ikakus.data.repository.RocketRepository
import com.mvvmexample.ikakus.monrocketlist.feature.rockets.RocketViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

class RocketListModule {
  val instance = applicationContext {
    factory {
      RocketRepository(
          get(),
          RemoteRocketDataSource(get()),
          LocalRocketDataSource(get())
      )
    }
    viewModel { RocketViewModel(get(), get()) }
  }
}