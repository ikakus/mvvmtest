package com.mvvmexample.ikakus.monrocketlist.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Immutable model class for a Rocket. In order to compile with Room, we can't use @JvmOverloads to
 * generate multiple constructors.
 *
 * @param name       name of the rocket
 * @param country    country that made rocket
 * @param enginesCount    rocket's engines count
 * @param isActive    rocket's status
 * @param id         id of the rocket
 */
@Entity(tableName = "rockets")
data class RocketDto @JvmOverloads constructor(
    @SerializedName("rocket_name")
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "country") var country: String = "",
    @ColumnInfo(name = "engines_count") var enginesCount: Int = 0,
    @ColumnInfo(name = "active") var isActive: Boolean = false,
    @PrimaryKey @ColumnInfo(name = "entryid") var id: Int = 0
)