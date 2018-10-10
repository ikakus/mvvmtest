package com.mvvmexample.ikakus.monrocketlist.feature.rockets.di

import com.mvvmexample.ikakus.data.datasource.rocket.LocalRocketDataSource
import com.mvvmexample.ikakus.data.datasource.rocket.RemoteRocketDataSource
import com.mvvmexample.ikakus.data.datasource.rocket.RocketsDataSource
import com.mvvmexample.ikakus.data.repository.RocketRepository
import com.mvvmexample.ikakus.monrocketlist.common.schedulers.SchedulerProvider
import com.mvvmexample.ikakus.monrocketlist.feature.rockets.RocketsViewModel
import org.koin.dsl.module.applicationContext

class RocketListModule {
  val instance = applicationContext {
    factory {
      RocketRepository(
          get(),
          RemoteRocketDataSource(get()),
          LocalRocketDataSource(get())
      ) as RocketsDataSource
    }
    bean {
      RocketsViewModel(SchedulerProvider(), get())
    }
  }
}