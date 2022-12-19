package com.luisfagundes.navigation

import com.luisfagundes.detail.RecipesNavGraph
import com.luisfagundes.home.HomeNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object RootNavGraph : NavGraphSpec {

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
    override val route = "root"
    override val startRoute = HomeNavGraph

    override val nestedNavGraphs = listOf(
        HomeNavGraph,
        RecipesNavGraph
    )
}