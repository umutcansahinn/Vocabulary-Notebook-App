package com.umutcansahin.languageword.feature_word.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.umutcansahin.languageword.feature_word.presentation.add_edit_word.AddEditWordScreen
import com.umutcansahin.languageword.feature_word.presentation.explore.ExploreScreen
import com.umutcansahin.languageword.feature_word.presentation.splash.SplashScreen
import com.umutcansahin.languageword.feature_word.presentation.words.WordsScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(
                navigateToWords = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = Screen.HomeScreen.route) {
            WordsScreen(navController = navController)
        }
        composable(
            route = Screen.HomeAddEditScreen.route +
                    "?wordId={wordId}&wordColor={wordColor}",
            arguments = listOf(
                navArgument(
                    name = "wordId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "wordColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            val color = it.arguments?.getInt("wordColor") ?: -1
            AddEditWordScreen(navController = navController, wordColor = color)
        }
        composable(route = Screen.ExploreScreen.route) {
            ExploreScreen()
        }
    }
}

