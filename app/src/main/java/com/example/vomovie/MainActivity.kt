package com.example.vomovie

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.vomovie.models.getMovies
import com.example.vomovie.nav.MovieNav
import com.example.vomovie.screens.home.HomeScreen
import com.example.vomovie.ui.theme.VOMovieTheme
import com.example.vomovie.viewModels.MovieViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("mainactivity", "oncreate called")
        val movies = getMovies()
        val vm: MovieViewModel by viewModels()
        setContent {

            //MyAppBar{
            MovieNav()
            //}

        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("mainactivity", "onstart called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "on Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "on Pause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "on Destroy")
    }
}


//New

@Composable
fun MyAppBar(content: @Composable () -> Unit) {
    VOMovieTheme {
        content
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VOMovieTheme {
        HomeScreen()
    }
}