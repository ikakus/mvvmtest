package com.mvvmexample.ikakus.data.repository

import com.mvvmexample.ikakus.data.data.RocketData
import com.mvvmexample.ikakus.data.datasource.rocket.RocketsDataSource
import io.reactivex.Single

class RocketRepository(private val remoteDataSource: RocketsDataSource,
                       private val localDataSource: RocketsDataSource) : RocketsDataSource {
  override fun getRocketDetails(rocketId: String): Single<RocketData> {
    return remoteDataSource.getRocketDetails(rocketId)
  }

  override fun getRockets(): Single<List<RocketData>> {
    return remoteDataSource.getRockets()
  }
}