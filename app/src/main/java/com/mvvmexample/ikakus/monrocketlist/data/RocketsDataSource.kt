package com.mvvmexample.ikakus.monrocketlist.data

import io.reactivex.Single

interface RocketsDataSource {
  fun getRockets() : Single<List<Rocket>>
}