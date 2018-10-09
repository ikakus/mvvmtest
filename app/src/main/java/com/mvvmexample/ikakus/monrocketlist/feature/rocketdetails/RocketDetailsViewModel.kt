package com.mvvmexample.ikakus.monrocketlist.feature.rocketdetails

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.mvvmexample.ikakus.data.data.RocketData
import com.mvvmexample.ikakus.data.repository.RocketRepository
import com.mvvmexample.ikakus.monrocketlist.common.schedulers.SchedulerProvider

class RocketDetailsViewModel(
    context: Application,
    private val rocketsRepository: RocketRepository
    ) : AndroidViewModel(context) {

  var rocketId: String? = null
  set(value) {
    value?.let{
      loadRocketDetails(it)
    }
  }
  val rocketData: ObservableField<RocketData> = ObservableField()
  val loading = ObservableBoolean(false)

  @SuppressLint("CheckResult")
  internal fun loadRocketDetails(rocketId: String) {
    rocketsRepository.getRocketDetails(rocketId)
        .toObservable()
        .observeOn(SchedulerProvider().ui())
        .doOnSubscribe { loading.set(true) }
        .doFinally { loading.set(false) }
        .subscribe { rocketDetails ->
          rocketData.set(rocketDetails)
        }
  }

}