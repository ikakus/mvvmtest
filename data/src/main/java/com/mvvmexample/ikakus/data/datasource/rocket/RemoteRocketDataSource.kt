package com.mvvmexample.ikakus.data.datasource.rocket

import com.mvvmexample.ikakus.data.data.RocketData
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

class RemoteRocketDataSource(retrofit: Retrofit) : RocketsDataSource {
  private var api = retrofit.
      create(RetrofitApi::class.java)

  override fun getRocketDetails(rocketId: String): Single<RocketData> {
    return api.getRocketDetails(rocketId)
  }

  override fun getRockets(): Single<List<RocketData>> {
    return api.getRockets()
  }

  interface RetrofitApi {
    @GET("rockets")
    fun getRockets() : Single<List<RocketData>>

    @GET("rockets/{id}")
    fun getRocketDetails(@Path("id") rocketId: String) : Single<RocketData>
  }
}