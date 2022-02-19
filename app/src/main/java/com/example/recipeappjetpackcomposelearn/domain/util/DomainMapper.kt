package com.example.recipeappjetpackcomposelearn.domain.util

interface DomainMapper<T, RecipeModel> {
    fun mapToDomainModel(entity: T): RecipeModel
    fun mapFromDomainModel(recipeModel: RecipeModel): T
}