package com.mvvmexample.ikakus.data.datasource.rocket

import com.mvvmexample.ikakus.data.entities.RocketEntity
import io.reactivex.Single

interface RocketsDataSource {
  fun getRockets() : Single<List<RocketEntity>>
  fun getRocketDetails(rocketId: String) : Single<RocketEntity>
}