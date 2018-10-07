package com.mvvmexample.ikakus.monrocketlist.feature.rocketdetails

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mvvmexample.ikakus.monrocketlist.R
import com.mvvmexample.ikakus.monrocketlist.databinding.ActivityRocketDetailsBinding
import org.koin.android.architecture.ext.viewModel

class RocketDetailsActivity: AppCompatActivity() {

  private val vModel: RocketDetailsViewModel by viewModel()

  private val rocketId by lazy { intent?.getStringExtra(EXTRA_ROCKET_ID) as String }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_rocket_details)

    val viewDataBinding: ActivityRocketDetailsBinding = DataBindingUtil.setContentView(
        this, R.layout.activity_rocket_details)

    viewDataBinding.viewmodel = vModel
    vModel.rocketId = rocketId
  }

  companion object {
    val EXTRA_ROCKET_ID = "rocketId"
  }
}