package com.mvvmexample.ikakus.monrocketlist.common

import android.arch.lifecycle.DefaultLifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface LifecycleDisposable: DefaultLifecycleObserver {
  val disposeBag: CompositeDisposable

  fun Disposable.addToBag() {
    disposeBag.add(this)
  }
  
  override fun onDestroy(owner: LifecycleOwner) {
    disposeBag.clear()
  }
}