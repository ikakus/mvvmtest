package com.mvvmexample.ikakus.data.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.mvvmexample.ikakus.data.entities.RocketEntity

@Entity
data class RocketDbEntity(
    @ColumnInfo(name = "engine_count") val engineCount: Int,
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "name") val name: String,
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String
) {
  constructor(rocketData: RocketEntity) :
      this(
          engineCount = rocketData.engineCount,
          country = rocketData.country,
          name = rocketData.name,
          id = rocketData.id
      )
}