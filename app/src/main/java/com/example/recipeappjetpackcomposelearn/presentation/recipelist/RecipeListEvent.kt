package com.example.recipeappjetpackcomposelearn.presentation.recipelist

sealed class RecipeListEvent {
    object NewSearchEvent: RecipeListEvent()
    object NextSearchEvent: RecipeListEvent()
}
