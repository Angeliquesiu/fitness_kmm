package com.example.fitnesswraps.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitnesswraps.android.exercise_detail.ExerciseDetailScreen
import com.example.fitnesswraps.android.exercise_list.ExerciseListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "exercise_list") {
                    composable(route = "exercise_list") {
                        ExerciseListScreen(navController = navController)
                    }
                    composable(
                        route = "exercise_detail/{exerciseId}",
                        arguments = listOf(
                            navArgument(name = "exerciseId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            }
                        )
                    ) {backStackEntry ->
                        val exerciseId = backStackEntry.arguments?.getLong("exerciseId") ?: -1L
                        ExerciseDetailScreen(exerciseId = exerciseId, navController = navController)
                    }
                }
            }
        }
    }
}