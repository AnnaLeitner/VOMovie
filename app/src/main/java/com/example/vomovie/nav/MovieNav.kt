package com.example.vomovie.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vomovie.home.DetailScreen
import com.example.vomovie.screens.Favs.FavScreen
import com.example.vomovie.screens.home.HomeScreen
import com.example.vomovie.viewModels.MovieViewModel

@Composable
fun MovieNav() {
    val navController = rememberNavController()
    val movieViewModel: MovieViewModel = viewModel()

    NavHost(navController = navController, startDestination = MovieScreens.Homescreen.name) {

        composable(MovieScreens.Homescreen.name) {
            HomeScreen(
                navController = navController,
                movieViewModel = movieViewModel
            )
        }

        //url bauen: ww.domain.com/detailscreen/movie=12
        composable(
            MovieScreens.DetailScreen.name + "/{movie}", //übergeben ein movie objekt an nav route
            arguments = listOf(navArgument("movie") { // konkret wird eine Id übergeben
                type = NavType.StringType
            })
        ) { backStackEntry ->

            // ?==> nich null so kann man ein element aus der nav herausholen
            // "movie" Z:27 ist abhängig von "movie" in Z: 21 und 22x
            DetailScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getString("movie"),
                movieViewModel = movieViewModel
            )
        }

        composable(MovieScreens.FavScreen.name) {
            FavScreen(
                navController = navController,
                movieViewModel = movieViewModel
            )
        }
    }

}