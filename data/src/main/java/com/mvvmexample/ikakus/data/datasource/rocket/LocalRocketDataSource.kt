package com.mvvmexample.ikakus.data.datasource.rocket

import com.mvvmexample.ikakus.data.data.RocketData
import io.reactivex.Single

class LocalRocketDataSource() : RocketsDataSource {
  override fun getRocketDetails(rocketId: String): Single<RocketData> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getRockets(): Single<List<RocketData>> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}