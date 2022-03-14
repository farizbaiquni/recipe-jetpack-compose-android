package com.example.recipeappjetpackcomposelearn.util

sealed class Screen(var route: String) {
    object RecipeList: Screen(route = "recipeList_screen")
    object RecipeDetails: Screen(route = "recipeDetails_screen")
}
