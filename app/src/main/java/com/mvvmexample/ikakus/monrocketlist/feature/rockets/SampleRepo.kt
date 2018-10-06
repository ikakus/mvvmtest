package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import com.mvvmexample.ikakus.monrocketlist.data.IRocketsRepository
import com.mvvmexample.ikakus.monrocketlist.data.Rocket
import io.reactivex.Single


class SampleRepo : IRocketsRepository {
  override fun getRockets(): Single<List<Rocket>> {
    val rockets = ArrayList<Rocket>()
    rockets.add(Rocket("1", "USA", 2, true, "123"))
    rockets.add(Rocket("2", "USA", 2, true, "123"))
    rockets.add(Rocket("3", "USA", 2, true, "123"))
    rockets.add(Rocket("4", "USA", 2, true, "123"))
    return Single.just(rockets)
  }

}