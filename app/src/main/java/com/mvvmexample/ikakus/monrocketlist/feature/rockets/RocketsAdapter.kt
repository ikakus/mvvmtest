package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.mvvmexample.ikakus.monrocketlist.data.RocketData
import com.mvvmexample.ikakus.monrocketlist.databinding.RocketItemBinding


class RocketsAdapter(
    private var rockets: List<RocketData>,
    private var viewModel: RocketViewModel) : BaseAdapter() {

  fun replaceData(list: List<RocketData>) {
    setList(list)
  }

  override fun getCount() = rockets.size

  override fun getItem(position: Int) = rockets[position]

  override fun getItemId(position: Int) = position.toLong()

  override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
    val binding: RocketItemBinding
    binding = if (view == null) {
      // Inflate
      val inflater = LayoutInflater.from(viewGroup.context)
      // Create the binding
      RocketItemBinding.inflate(inflater, viewGroup, false)
    } else {
      // Recycling view
      DataBindingUtil.getBinding(view)!!
    }

    val userActionsListener = object : RocketItemUserActionsListener {
      override fun onRocketClicked(rocket: RocketData) {
        viewModel.openRocketEvent.value = rocket.rocket_id
      }

    }

    with(binding) {
      rocket = rockets[position]
      listener = userActionsListener
      executePendingBindings()
    }

    return binding.root
  }

  private fun setList(list: List<RocketData>) {
    this.rockets = list
    notifyDataSetChanged()
  }
}
