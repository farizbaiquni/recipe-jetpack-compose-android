package com.example.recipeappjetpackcomposelearn.network.response

import com.example.recipeappjetpackcomposelearn.network.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeResponses(
    @SerializedName("count")
    val count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>
)
