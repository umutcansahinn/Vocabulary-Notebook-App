package com.umutcansahin.languageword.feature_word.navigation

import com.umutcansahin.languageword.R
import com.umutcansahin.languageword.common.Constants

sealed class BottomNavItem(
    val title: String,
    val image: Int,
    val route: String
) {
    object Home: BottomNavItem(
        title = Constants.BOTTOM_BAR_TITLE_HOME,
        image = R.drawable.add_home_image,
        route = Screen.HomeScreen.route
    )

    object Explore: BottomNavItem(
        title = Constants.BOTTOM_BAR_TITLE_EXPLORE,
        image = R.drawable.explore_image,
        route = Screen.ExploreScreen.route
    )
}