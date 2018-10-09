package com.mvvmexample.ikakus.data.entities

import com.mvvmexample.ikakus.data.data.RocketData
import com.mvvmexample.ikakus.data.room.model.RocketDbEntity

data class RocketEntity(
     val engineCount: Int,
     val country: String,
     val name: String,
     val id: String
) {
  constructor(rocketDbEntity: RocketDbEntity) :
      this(
          engineCount = rocketDbEntity.engineCount,
          country = rocketDbEntity.country,
          name = rocketDbEntity.name,
          id = rocketDbEntity.id
      )

  constructor(rocketData: RocketData) :
      this(
          engineCount = rocketData.engines.number,
          country = rocketData.country,
          name = rocketData.rocket_name,
          id = rocketData.rocket_id
      )
}