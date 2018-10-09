package com.mvvmexample.ikakus.monrocketlist.di

import android.arch.persistence.room.Room
import android.content.Context
import com.mvvmexample.ikakus.data.room.RocketDatabase
import org.koin.dsl.module.applicationContext

class DatabaseModule(context: Context) {
    val instance = applicationContext {
        val db = Room.databaseBuilder(context,
                RocketDatabase::class.java, "rockets-db").build()
        bean {
            db.rocketDao()
        }
    }

}