package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvmexample.ikakus.monrocketlist.databinding.RocketListFragBinding

class RocketsFragment : Fragment() {

  private lateinit var viewDataBinding: RocketListFragBinding
  private lateinit var rocketsAdapter: RocketsAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    viewDataBinding = RocketListFragBinding
        .inflate(inflater, container, false)
        .apply {
          viewmodel = RocketViewModel(
              context?.applicationContext as Application,
              SampleRepo()
              )
        }
    return viewDataBinding.root
  }

  override fun onResume() {
    super.onResume()
    viewDataBinding.viewmodel?.start()
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupListAdapter()
  }

  private fun setupListAdapter() {
    val viewModel = viewDataBinding.viewmodel
    if (viewModel != null) {
      rocketsAdapter = RocketsAdapter(ArrayList(0))
      viewDataBinding.rocketsList.adapter = rocketsAdapter
    } else {
      Log.w(TAG, "ViewModel not initialized when attempting to set up adapter.")
    }
  }
  companion object {
    fun newInstance() = RocketsFragment()
    private const val TAG = "RocketsFragment"

  }

}