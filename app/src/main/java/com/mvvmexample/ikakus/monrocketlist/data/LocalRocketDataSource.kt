package com.mvvmexample.ikakus.monrocketlist.data

import io.reactivex.Single

class LocalRocketDataSource() : RocketsDataSource{
  override fun getRocketDetails(rocketId: String): Single<RocketData> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getRockets(): Single<List<RocketData>> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}