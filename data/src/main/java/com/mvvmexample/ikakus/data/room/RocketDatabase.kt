package com.mvvmexample.ikakus.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.mvvmexample.ikakus.data.room.model.RocketDbEntity


@Database(entities = arrayOf(RocketDbEntity::class), version = 1)
abstract class RocketDatabase : RoomDatabase() {
    abstract fun rocketDao(): RocketDao
}