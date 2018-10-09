package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableBoolean
import com.jakewharton.rxrelay2.PublishRelay
import com.mvvmexample.ikakus.data.data.RocketData
import com.mvvmexample.ikakus.data.repository.RocketRepository
import com.mvvmexample.ikakus.monrocketlist.common.SingleLiveEvent
import com.mvvmexample.ikakus.monrocketlist.common.schedulers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class RocketViewModel(
    context: Application,
    private val rocketsRepository: RocketRepository
) : AndroidViewModel(context) {

  private val compositeDisposable = CompositeDisposable()
  val items: PublishRelay<List<RocketData>> = PublishRelay.create()
  val loading = ObservableBoolean(false)
  internal val openRocketEvent = SingleLiveEvent<String>()
  var showRecyclerLoading = ObservableBoolean(false)

  fun start() {
    loadRockets()
  }

  internal fun loadRockets() {
    rocketsRepository.getRockets()
        .observeOn(SchedulerProvider().ui())
        .doOnSubscribe {
          loading.set(true)
        }
        .doFinally {
          loading.set(false)
          showRecyclerLoading.set(true)
        }
        .subscribe { rockets ->
          items.accept(rockets)
        }.addTo(compositeDisposable)
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}