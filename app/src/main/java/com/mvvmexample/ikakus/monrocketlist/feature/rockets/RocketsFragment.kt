package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import android.databinding.ObservableList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvmexample.ikakus.monrocketlist.data.Rocket
import com.mvvmexample.ikakus.monrocketlist.databinding.RocketListFragBinding
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

      bindListItems(viewModel)
    } else {
      Log.w(TAG, "ViewModel not initialized when attempting to set up adapter.")
    }
  }

  private fun bindListItems(viewModel: RocketViewModel) {
    viewModel.items.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<Rocket>>() {
      override fun onChanged(sender: ObservableList<Rocket>?) {
        rocketsAdapter.replaceData(sender?.toList()!!)
      }

      override fun onItemRangeRemoved(sender: ObservableList<Rocket>?, positionStart: Int, itemCount: Int) {
        rocketsAdapter.replaceData(sender?.toList()!!)
      }

      override fun onItemRangeMoved(sender: ObservableList<Rocket>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
        rocketsAdapter.replaceData(sender?.toList()!!)
      }

      override fun onItemRangeInserted(sender: ObservableList<Rocket>?, positionStart: Int, itemCount: Int) {
        rocketsAdapter.replaceData(sender?.toList()!!)
      }

      override fun onItemRangeChanged(sender: ObservableList<Rocket>?, positionStart: Int, itemCount: Int) {
        rocketsAdapter.replaceData(sender?.toList()!!)
      }

    })
  }

  companion object {
    fun newInstance() = RocketsFragment()
    private const val TAG = "RocketsFragment"

  }

}