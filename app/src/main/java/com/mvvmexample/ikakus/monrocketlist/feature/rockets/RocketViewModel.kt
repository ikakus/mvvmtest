package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.jakewharton.rxrelay2.PublishRelay
import com.mvvmexample.ikakus.data.entities.RocketEntity
import com.mvvmexample.ikakus.data.repository.RocketRepository
import com.mvvmexample.ikakus.monrocketlist.common.SingleLiveEvent
import com.mvvmexample.ikakus.monrocketlist.common.schedulers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class RocketViewModel(
    private val rocketsRepository: RocketRepository
) : ViewModel() {

  private val compositeDisposable = CompositeDisposable()
  val items: PublishRelay<List<RocketEntity>> = PublishRelay.create()
  val loading = ObservableBoolean(false)
  var enableRecyclerLoading = ObservableBoolean(false)
  var showSomethingWrong = ObservableBoolean(false)
  var onlyActive = ObservableBoolean(false)
  internal val openRocketEvent = SingleLiveEvent<String>()
  private var rockets :List<RocketEntity> = emptyList()

  fun setActiveFilter(active :Boolean){
    onlyActive.set(active)
    items.accept(getRockets(active))
  }

  fun start() {
    loadRockets()
  }

  internal fun loadRockets() {
    rocketsRepository.getRockets()
        .subscribeOn(SchedulerProvider().io())
        .observeOn(SchedulerProvider().ui())
        .doOnSubscribe {
          loading.set(true)
          showSomethingWrong.set(false)
        }
        .doOnError {
          showSomethingWrong.set(true)
        }
        .doFinally {
          loading.set(false)
          enableRecyclerLoading.set(true)
        }
        .subscribe { rockets ->
          this.rockets = rockets
          items.accept(
              getRockets(onlyActive.get())
          )
        }
        .addTo(compositeDisposable)
  }

  private fun getRockets(active: Boolean): List<RocketEntity>{
    return if(active){
      rockets.filter { it.active }
    }else{
      rockets
    }
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}