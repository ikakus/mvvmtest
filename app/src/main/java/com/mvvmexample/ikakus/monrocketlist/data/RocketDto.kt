/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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