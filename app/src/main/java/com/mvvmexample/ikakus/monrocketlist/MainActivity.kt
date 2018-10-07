package com.mvvmexample.ikakus.monrocketlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mvvmexample.ikakus.monrocketlist.feature.rockets.RocketsFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val fragmentManager = this.supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.container, RocketsFragment.newInstance())
    fragmentTransaction.commit()
  }
}
