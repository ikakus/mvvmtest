package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.mvvmexample.ikakus.monrocketlist.data.Rocket
import com.mvvmexample.ikakus.monrocketlist.databinding.RocketItemBinding


class RocketsAdapter(
    private var rockets: List<Rocket>,
    private var viewModel: RocketViewModel) : BaseAdapter() {

  fun replaceData(list: List<Rocket>) {
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
      override fun onRocketClicked(rocket: Rocket) {
        viewModel.openRocketEvent.value = rocket.id
      }

    }

    with(binding) {
      rocket = rockets[position]
      listener = userActionsListener
      executePendingBindings()
    }

    return binding.root
  }

  private fun setList(list: List<Rocket>) {
    this.rockets = list
    notifyDataSetChanged()
  }
}
