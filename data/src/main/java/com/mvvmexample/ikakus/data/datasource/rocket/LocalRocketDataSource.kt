package com.mvvmexample.ikakus.data.datasource.rocket

import com.mvvmexample.ikakus.data.entities.RocketEntity
import com.mvvmexample.ikakus.data.room.RocketDao
import com.mvvmexample.ikakus.data.room.model.RocketDbEntity
import io.reactivex.Single

class LocalRocketDataSource(private val rocketDao: RocketDao) : RocketsDataSource {
  override fun getRocketDetails(rocketId: String): Single<RocketEntity> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getRockets(): Single<List<RocketEntity>> {
    return rocketDao.getAll().map { list-> list.map { item -> RocketEntity(item) } }
  }

  fun saveRockets(rockets :List<RocketEntity>){
    rocketDao.insertAll(rockets.map { RocketDbEntity(it) })
  }

}