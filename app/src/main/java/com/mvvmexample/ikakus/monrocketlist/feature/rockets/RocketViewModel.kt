package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import android.widget.ListView
import com.mvvmexample.ikakus.monrocketlist.data.IRocketsRepository
import com.mvvmexample.ikakus.monrocketlist.data.Rocket

class RocketViewModel(
    context: Application,
    private val rocketsRepository: IRocketsRepository
) : AndroidViewModel(context) {

  val items: ObservableList<Rocket> = ObservableArrayList()

  val numberOfActiveTasksString = ObservableField<String>()

  fun start() {
    numberOfActiveTasksString.set("asfsdf")
    loadRockets()
  }

  @SuppressLint("CheckResult")
  private fun loadRockets() {
    rocketsRepository.getRockets()
        .subscribe{ rockets ->
          with(items) {
            clear()
            addAll(rockets)
          }
        }
  }

  @BindingAdapter("listItems")
  fun setItems(listView: ListView, items: List<Rocket>) {
    with(listView.adapter as RocketsAdapter) {
      replaceData(items)
    }
  }
}