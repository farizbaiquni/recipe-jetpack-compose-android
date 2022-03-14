package com.example.recipeappjetpackcomposelearn.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeappjetpackcomposelearn.presentation.recipelist.RecipeList

@Composable
fun SetupNavGraph( navController: NavHostController ){
    NavHost(navController = navController, startDestination = Screen.RecipeList.route){
        composable(
            route = Screen.RecipeList.route,
        ){
            RecipeList()
        }
    }
}