package com.umutcansahin.languageword.feature_word.navigation

sealed class Screen (val route: String) {

    object Splash : Screen("splash_screen")
    object  HomeAddEditScreen: Screen("home_add_edit_screen")
    object HomeScreen : Screen("home_screen")
    object ExploreScreen: Screen("explore_screen")


}