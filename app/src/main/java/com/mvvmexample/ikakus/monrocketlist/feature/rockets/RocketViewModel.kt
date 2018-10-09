package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableBoolean
import com.mvvmexample.ikakus.data.RocketData
import com.mvvmexample.ikakus.monrocketlist.common.SingleLiveEvent
import com.mvvmexample.ikakus.data.RocketRepository
import com.mvvmexample.ikakus.monrocketlist.schedulers.SchedulerProvider
import io.reactivex.subjects.PublishSubject

class RocketViewModel(
    context: Application,
    private val rocketsRepository: RocketRepository
) : AndroidViewModel(context) {

  val items: PublishSubject<List<RocketData>> = PublishSubject.create()
  val loading = ObservableBoolean(false)
  internal val openRocketEvent = SingleLiveEvent<String>()

  fun start() {
    loadRockets()
  }

  @SuppressLint("CheckResult")
  internal fun loadRockets() {
    rocketsRepository.getRockets()
        .toObservable()
        .observeOn(SchedulerProvider().ui())
        .doOnSubscribe { loading.set(true) }
        .doFinally { loading.set(false) }
        .subscribe{ rockets ->
          items.onNext(rockets)
        }
  }
}