package com.mvvmexample.ikakus.monrocketlist.feature.rockets.di

import com.mvvmexample.ikakus.monrocketlist.data.LocalRocketDataSource
import com.mvvmexample.ikakus.monrocketlist.data.RemoteRocketDataSource
import com.mvvmexample.ikakus.monrocketlist.data.RocketRepository
import com.mvvmexample.ikakus.monrocketlist.feature.rockets.RocketViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

class RocketListModule {
  val instance = applicationContext {
    factory {
      RocketRepository(
          RemoteRocketDataSource(get()),
          LocalRocketDataSource()
      )
//      SampleRepo()
    }
    viewModel { RocketViewModel(get(), get()) }
  }
}