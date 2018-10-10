package com.mvvmexample.ikakus.monrocketlist.feature.rocketdetails

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.mvvmexample.ikakus.data.entities.RocketEntity
import com.mvvmexample.ikakus.data.repository.RocketRepository
import com.mvvmexample.ikakus.monrocketlist.common.schedulers.SchedulerProvider

class RocketDetailsViewModel(
    private val rocketsRepository: RocketRepository
    ) : ViewModel() {

  var rocketId: String? = null
  set(value) {
    value?.let{
      loadRocketDetails(it)
    }
  }
  val rocketData: ObservableField<RocketEntity> = ObservableField()
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