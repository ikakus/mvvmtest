package com.mvvmexample.ikakus.monrocketlist.feature.rocketdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mvvmexample.ikakus.monrocketlist.R

class RocketDetailsActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_recket_details)

  }

  companion object {
    val EXTRA_ROCKET_ID = "rocketId"
  }
}