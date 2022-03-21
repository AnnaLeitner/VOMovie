package com.example.vomovie.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vomovie.home.DetailScreen
import com.example.vomovie.screens.home.HomeScreen

@Composable
fun MovieNav(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homescreen"){
        composable("homescreen"){ HomeScreen(navController = navController)}

        //url bauen: ww.domain.com/detailscreen/movie=12
        composable("detailscreen/{movie}", //übergeben ein movie objekt an nav route
            arguments = listOf(navArgument("movie") { // konkret wird eine Id übergeben
                type = NavType.StringType
            })
            ){ backStackEntry ->

            // ?==> nich null so kann man ein element aus der nav heruasholen
            // "movie" Z:27 ist abhängig von "movie" in Z: 21 und 22
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movie"))
        }
    }

}