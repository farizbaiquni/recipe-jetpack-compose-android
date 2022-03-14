package com.example.recipeappjetpackcomposelearn.presentation.recipelist

sealed class RecipeListEvent {
    object NewQueryEvent: RecipeListEvent()
    object NextQueryEvent: RecipeListEvent()
}
