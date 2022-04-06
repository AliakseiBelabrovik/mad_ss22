package com.example.testapp_video2.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testapp_video2.viewmodels.FavoritesViewModel
import com.example.testapp_video2.screens.detailscreen.DetailScreen
import com.example.testapp_video2.screens.favorites.FavoritesScreen
import com.example.testapp_video2.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    val favoritesViewModel : FavoritesViewModel = viewModel()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.value) {
        composable(MovieScreens.HomeScreen.value) { HomeScreen(navController = navController, viewModel = favoritesViewModel) }
        composable(
            route = "${MovieScreens.DetailScreen.value}/{movieId}",
            arguments = listOf(
                navArgument(name = "movieId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            DetailScreen( navController = navController, favViewModel = favoritesViewModel, movieId = backStackEntry.arguments?.getString("movieId"))
        }
        composable(MovieScreens.FavoritesScreen.value) { FavoritesScreen(navController = navController, viewModel = favoritesViewModel) }
    }
}