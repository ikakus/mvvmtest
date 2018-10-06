package com.mvvmexample.ikakus.monrocketlist.data

import io.reactivex.Single

class RocketRepository(private val remoteDataSource: RocketsDataSource,
                       private val localDataSource: RocketsDataSource) : RocketsDataSource {

  override fun getRockets(): Single<List<Rocket>> {
    return remoteDataSource.getRockets()
  }
}