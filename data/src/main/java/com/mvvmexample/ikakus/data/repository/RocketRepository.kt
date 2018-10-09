package com.mvvmexample.ikakus.data.repository

import com.mvvmexample.ikakus.data.datasource.rocket.LocalRocketDataSource
import com.mvvmexample.ikakus.data.datasource.rocket.RemoteRocketDataSource
import com.mvvmexample.ikakus.data.datasource.rocket.RocketsDataSource
import com.mvvmexample.ikakus.data.entities.RocketEntity
import io.reactivex.Single

class RocketRepository(private val remoteDataSource: RemoteRocketDataSource,
                       private val localDataSource: LocalRocketDataSource) : RocketsDataSource {
  override fun getRocketDetails(rocketId: String): Single<RocketEntity> {
    return remoteDataSource.getRocketDetails(rocketId)
  }

  var returnLocal = true

  override fun getRockets(): Single<List<RocketEntity>> {
    return if (returnLocal) {
      localDataSource.getRockets()
    } else {
      remoteDataSource.getRockets().doOnSuccess { localDataSource.saveRockets(it) }
    }
  }
}