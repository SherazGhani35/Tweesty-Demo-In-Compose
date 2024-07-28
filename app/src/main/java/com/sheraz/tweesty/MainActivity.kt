package com.sheraz.tweesty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sheraz.tweesty.api.TweetApi
import com.sheraz.tweesty.screens.CategoryScreen
import com.sheraz.tweesty.screens.DetailScreen
import com.sheraz.tweesty.ui.theme.TweestyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tweetApi: TweetApi

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TweestyTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        title = { Text(text = "Tweesty") },
                    )
                }) {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(1f)
                    ) {
                        App()
                    }

                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category") {

        //Category Screen in Nav Graph
        composable("category") {
            CategoryScreen {
                navController.navigate("detail/$it")
            }
        }

        //Category Detail Screen in Nav Graph
        composable(
            "detail/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) {
            DetailScreen()
        }
    }
}

