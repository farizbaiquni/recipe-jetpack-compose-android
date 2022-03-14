package com.example.recipeappjetpackcomposelearn.presentation

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipeappjetpackcomposelearn.presentation.components.CircularIndeterminateProgressBar
import com.example.recipeappjetpackcomposelearn.presentation.components.RecipeCard
import com.example.recipeappjetpackcomposelearn.presentation.components.SearchAppBar
import com.example.recipeappjetpackcomposelearn.presentation.recipelist.PAGE_SIZE
import com.example.recipeappjetpackcomposelearn.presentation.recipelist.RecipeListViewModel
import com.example.recipeappjetpackcomposelearn.presentation.utils.ShimmerRecipeListAnimation
import com.example.recipeappjetpackcomposelearn.ui.theme.RecipeAppJetpackComposeLearnTheme
import com.example.recipeappjetpackcomposelearn.util.SetupNavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    @Inject
    lateinit @Named("auth_token") var random: String

    private val recipeListViewModel: RecipeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            navController = rememberNavController()
            val isDarkMode = recipeListViewModel.isDarkMode.value

            RecipeAppJetpackComposeLearnTheme(darkTheme = isDarkMode) {

                this.window.statusBarColor = if(isDarkMode){
                    Color.parseColor("#121212")
                } else {
                    Color.parseColor("#087f23")
                }

                SetupNavGraph(navController)

            }
        } // End setContent
    } // End onCreate
}// End MainActivity