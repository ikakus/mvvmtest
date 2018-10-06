package com.mvvmexample.ikakus.monrocketlist.data

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET

class RemoteRocketDataSource(retrofit: Retrofit) : RocketsDataSource{
  private var api = retrofit.
      create(RetrofitApi::class.java)
  override fun getRockets(): Single<List<Rocket>> {
    return api.getRockets()
  }

  interface RetrofitApi {
    @GET("rockets")
    fun getRockets() : Single<List<Rocket>>
  }
}