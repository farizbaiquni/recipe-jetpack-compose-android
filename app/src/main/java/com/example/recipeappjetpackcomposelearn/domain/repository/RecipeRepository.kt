package com.example.recipeappjetpackcomposelearn.domain.repository

import com.example.recipeappjetpackcomposelearn.domain.model.RecipeModel

interface RecipeRepository {
    suspend fun search(token: String, page: Int, query: String): List<RecipeModel>
    suspend fun get(token: String, id: Int): RecipeModel
}