package com.mvvmexample.ikakus.monrocketlist.feature.rockets

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvvmexample.ikakus.data.RocketData
import com.mvvmexample.ikakus.monrocketlist.databinding.RocketItemBinding


class RocketsAdapter(private var viewModel: RocketViewModel)
  : RecyclerView.Adapter<RocketsAdapter.RocketViewHolder>() {

  var rockets: List<RocketData> = emptyList()
  set(value) {
    field = value
    notifyDataSetChanged()
  }

  override fun getItemCount() = rockets.size

  override fun getItemId(position: Int) = position.toLong()

  override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RocketViewHolder {
    val inflater = LayoutInflater.from(viewGroup.context)
    val binding = RocketItemBinding.inflate(inflater, viewGroup, false)
    return RocketViewHolder(binding)
  }

  override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
    val userActionsListener = object : RocketItemUserActionsListener {
      override fun onRocketClicked(rocket: RocketData) {
        viewModel.openRocketEvent.value = rocket.rocket_id
      }
    }

    with(holder.binding) {
      rocket = rockets[position]
      listener = userActionsListener
      executePendingBindings()
    }
  }

  inner class RocketViewHolder(val binding: RocketItemBinding) : RecyclerView.ViewHolder(binding.root)
}
