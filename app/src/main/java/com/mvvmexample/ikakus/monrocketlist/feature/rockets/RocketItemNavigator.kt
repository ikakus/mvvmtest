package com.mvvmexample.ikakus.monrocketlist.feature.rockets

/**
 * Defines the navigation actions that can be called from a list item in the task list.
 */
interface RocketItemNavigator {
    fun openRocketDetails(rocketId: Int)
}
