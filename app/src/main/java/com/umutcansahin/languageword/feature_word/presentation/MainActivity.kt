package com.umutcansahin.languageword.feature_word.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.umutcansahin.languageword.feature_word.navigation.BottomNavigationBar
import com.umutcansahin.languageword.feature_word.navigation.NavGraph
import com.umutcansahin.languageword.feature_word.navigation.Screen
import com.umutcansahin.languageword.feature_word.presentation.add_edit_word.AddEditWordScreen
import com.umutcansahin.languageword.feature_word.presentation.words.WordsScreen
import com.umutcansahin.languageword.ui.theme.LanguageWordTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LanguageWordTheme {
                val bottomBarState = rememberSaveable { (mutableStateOf(false)) }

                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()

                when (navBackStackEntry?.destination?.route) {
                    Screen.Splash.route -> bottomBarState.value = false
                    else -> bottomBarState.value = true
                }
                Scaffold(
                    backgroundColor = Color.Gray,
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController,
                            bottomBarState = bottomBarState
                        )
                    }
                ) {
                    NavGraph(navController = navController, paddingValues = it)
                }
            }
        }
    }
}
