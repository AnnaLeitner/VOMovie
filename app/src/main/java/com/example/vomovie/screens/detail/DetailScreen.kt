package com.example.vomovie.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.vomovie.models.Movie
import com.example.vomovie.models.getMovies
import com.example.vomovie.ui.theme.VOMovieTheme

@Preview(showBackground = true)
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String? = "t0499549"
    ){
    MainContent(){
        Text(text = "Detail Screen $movieId")
    }
}

@Composable
fun MainContent(content: @Composable () -> Unit){

    Scaffold(
        topBar = {
            TopAppBar( backgroundColor = Color.Cyan, elevation = 3.dp){
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {

                        }
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Movie xy")
                }
            }
        }) {
        content()

    }


}