package com.example.tweetsy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetsy.api.TweetsAPI
import com.example.tweetsy.screens.CategoryList
import com.example.tweetsy.screens.TweetsList
import com.example.tweetsy.ui.theme.TweetsyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TweetsyTheme {
                App()
            }
        }
    }

    @Composable
    private fun App() {
        val navController = rememberNavController()

        Scaffold { innerPadding -> // ✅ Get innerPadding from Scaffold
            NavHost(
                navController,
                startDestination = "category",
                modifier = Modifier.padding(innerPadding) // ✅ Apply padding to NavHost
            ) {
                composable("category") {
                    CategoryList(modifier = Modifier.fillMaxSize()) { category ->
                        navController.navigate("tweets/$category")
                    }
                }
                composable(
                    "tweets/{category}",
                    arguments = listOf(navArgument("category") { type = NavType.StringType })
                ) { backStackEntry ->
                    val category = backStackEntry.arguments?.getString("category") ?: "Unknown"
                    TweetsList(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}