package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvmexample.ikakus.monrocketlist.common.LifecycleDisposable
import com.mvvmexample.ikakus.monrocketlist.databinding.RocketListFragBinding
import com.mvvmexample.ikakus.monrocketlist.feature.rocketdetails.RocketDetailsActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.rocket_list_frag.*
import org.koin.android.architecture.ext.viewModel

class RocketsFragment : Fragment(), LifecycleDisposable {

  private lateinit var viewDataBinding: RocketListFragBinding
  private lateinit var rocketsAdapter: RocketsAdapter
  override val disposeBag = CompositeDisposable()
  private val vModel: RocketViewModel by viewModel()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    viewDataBinding = RocketListFragBinding
        .inflate(inflater, container, false)
        .apply {
          viewmodel = vModel
        }
    return viewDataBinding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewDataBinding.viewmodel?.start()

  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setupListAdapter()
  }

  private fun setupListAdapter() {
    val viewModel = viewDataBinding.viewmodel
    if (viewModel != null) {
      rocketsAdapter = RocketsAdapter(viewModel)
      viewDataBinding.rocketsList.adapter = rocketsAdapter
      swipe_to_refresh.setOnRefreshListener {
        viewModel.loadRockets()
      }

      viewModel.openRocketEvent.observe(this, Observer { rocketId ->
        if (rocketId != null) {
          openRocketDetails(rocketId)
        }
      })

      viewModel.items.subscribe {
        rocketsAdapter.rockets = it
      }.addToBag()
    } else {
      Log.w(TAG, "ViewModel not initialized when attempting to set up adapter.")
    }
  }

  private fun openRocketDetails(rocketId: String) {
    val intent = Intent(context, RocketDetailsActivity::class.java).apply {
      putExtra(RocketDetailsActivity.EXTRA_ROCKET_ID, rocketId)
    }
    startActivity(intent)
  }

  companion object {
    fun newInstance() = RocketsFragment()
    private const val TAG = "RocketsFragment"

  }

}