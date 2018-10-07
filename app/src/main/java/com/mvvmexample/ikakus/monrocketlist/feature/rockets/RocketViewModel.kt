package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import android.widget.ListView
import com.mvvmexample.ikakus.monrocketlist.SingleLiveEvent
import com.mvvmexample.ikakus.monrocketlist.data.RocketData
import com.mvvmexample.ikakus.monrocketlist.data.RocketRepository
import com.mvvmexample.ikakus.monrocketlist.schedulers.SchedulerProvider

class RocketViewModel(
    context: Application,
    private val rocketsRepository: RocketRepository
) : AndroidViewModel(context) {

  val items: ObservableList<RocketData> = ObservableArrayList()
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
          with(items) {
            clear()
            addAll(rockets)
          }
        }
  }

  @BindingAdapter("listItems")
  fun setItems(listView: ListView, items: List<RocketData>) {
    with(listView.adapter as RocketsAdapter) {
      replaceData(items)
    }
  }
}