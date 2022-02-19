package com.example.recipeappjetpackcomposelearn.domain.repository

import com.example.recipeappjetpackcomposelearn.domain.model.RecipeModel
import com.example.recipeappjetpackcomposelearn.network.RecipeDtoMapper
import com.example.recipeappjetpackcomposelearn.network.response.RecipeService

class RecipeRepository_Impl(
    private val recipeService: RecipeService,
    private val recipeDtoMapper: RecipeDtoMapper
): RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<RecipeModel> {
        return recipeDtoMapper.toDomainModel(recipeService.search(token, page, query).recipes)
    }

    override suspend fun get(token: String, id: Int): RecipeModel {
        return recipeDtoMapper.mapToDomainModel(recipeService.get(token, id))
    }
}