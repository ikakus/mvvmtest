package com.mvvmexample.ikakus.monrocketlist.data

import io.reactivex.Single

class LocalRocketDataSource() : RocketsDataSource{
  override fun getRockets(): Single<List<Rocket>> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}