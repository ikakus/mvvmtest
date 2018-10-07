package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import com.mvvmexample.ikakus.monrocketlist.data.RocketData
import com.mvvmexample.ikakus.monrocketlist.data.RocketsDataSource
import io.reactivex.Single


class SampleRepo : RocketsDataSource {
  override fun getRocketDetails(rocketId: String): Single<RocketData> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getRockets(): Single<List<RocketData>> {
    val rockets = ArrayList<RocketData>()
//    rockets.add(Rocket("1", "USA", 2, true, 1))
//    rockets.add(Rocket("2", "USA", 2, true, 2))
//    rockets.add(Rocket("3", "USA", 2, true, 3))
//    rockets.add(Rocket("4", "USA", 2, true, 4))
    return Single.just(rockets)
  }

}