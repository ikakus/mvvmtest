package com.mvvmexample.ikakus.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.mvvmexample.ikakus.data.room.model.RocketDbEntity
import io.reactivex.Single

@Dao
interface RocketDao {
    @Query("SELECT * FROM RocketDbEntity")
    fun getAll(): Single<List<RocketDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(list: List<RocketDbEntity>)
}