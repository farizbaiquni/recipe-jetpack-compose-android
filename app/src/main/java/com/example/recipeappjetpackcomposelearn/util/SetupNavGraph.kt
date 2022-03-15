package com.example.recipeappjetpackcomposelearn.util

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipeappjetpackcomposelearn.presentation.recipe.RecipeDetails
import com.example.recipeappjetpackcomposelearn.presentation.recipelist.RecipeList
import com.example.recipeappjetpackcomposelearn.presentation.recipelist.RecipeListViewModel

@ExperimentalMaterialApi
@Composable
fun SetupNavGraph( navController: NavHostController, recipeListViewModel: RecipeListViewModel ){
    NavHost(navController = navController, startDestination = Screen.RecipeList.route){

        composable(
            route = Screen.RecipeList.route
        ){
            RecipeList(navController, recipeListViewModel)
        }

        composable(
            route = Screen.RecipeDetails.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = "default"
                    nullable = true
                }
            )
        ){
            RecipeDetails(idRecipe = it.arguments?.getString("id").toString())
        }
    }

}