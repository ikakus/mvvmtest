package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.ObservableList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvmexample.ikakus.monrocketlist.data.RocketData
import com.mvvmexample.ikakus.monrocketlist.databinding.RocketListFragBinding
import com.mvvmexample.ikakus.monrocketlist.feature.rocketdetails.RocketDetailsActivity
import kotlinx.android.synthetic.main.rocket_list_frag.*
import org.koin.android.architecture.ext.viewModel

class RocketsFragment : Fragment() {

  private lateinit var viewDataBinding: RocketListFragBinding
  private lateinit var rocketsAdapter: RocketsAdapter
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
      rocketsAdapter = RocketsAdapter(ArrayList(0), viewModel)
      viewDataBinding.rocketsList.adapter = rocketsAdapter
      swipe_to_refresh.setOnRefreshListener {
        viewModel.loadRockets()
      }

      viewModel.openRocketEvent.observe(this, Observer { rocketId ->
        if (rocketId != null) {
          openRocketDetails(rocketId)
        }
      })

      bindListItems(viewModel)
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

  private fun bindListItems(viewModel: RocketViewModel) {
    viewModel.items.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<RocketData>>() {
      override fun onChanged(sender: ObservableList<RocketData>?) {
        rocketsAdapter.replaceData(sender?.toList()!!)
      }

      override fun onItemRangeRemoved(sender: ObservableList<RocketData>?, positionStart: Int, itemCount: Int) {
        rocketsAdapter.replaceData(sender?.toList()!!)
      }

      override fun onItemRangeMoved(sender: ObservableList<RocketData>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
        rocketsAdapter.replaceData(sender?.toList()!!)
      }

      override fun onItemRangeInserted(sender: ObservableList<RocketData>?, positionStart: Int, itemCount: Int) {
        rocketsAdapter.replaceData(sender?.toList()!!)
      }

      override fun onItemRangeChanged(sender: ObservableList<RocketData>?, positionStart: Int, itemCount: Int) {
        rocketsAdapter.replaceData(sender?.toList()!!)
      }

    })
  }

  companion object {
    fun newInstance() = RocketsFragment()
    private const val TAG = "RocketsFragment"

  }

}