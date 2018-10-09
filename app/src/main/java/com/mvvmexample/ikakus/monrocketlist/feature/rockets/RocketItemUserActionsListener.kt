package com.mvvmexample.ikakus.monrocketlist.feature.rockets


import com.mvvmexample.ikakus.data.data.RocketData

/**
 * Listener used with data binding to process user actions.
 */
interface RocketItemUserActionsListener {
    fun onRocketClicked(rocket: RocketData)
}
