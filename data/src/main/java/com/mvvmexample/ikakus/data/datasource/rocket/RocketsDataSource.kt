package com.mvvmexample.ikakus.data.datasource.rocket

import com.mvvmexample.ikakus.data.data.RocketData
import io.reactivex.Single

interface RocketsDataSource {
  fun getRockets() : Single<List<RocketData>>
  fun getRocketDetails(rocketId: String) : Single<RocketData>
}