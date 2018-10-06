package com.mvvmexample.ikakus.monrocketlist.data

import io.reactivex.Single

interface IRocketsRepository {
  fun getRockets() : Single<List<Rocket>>
}