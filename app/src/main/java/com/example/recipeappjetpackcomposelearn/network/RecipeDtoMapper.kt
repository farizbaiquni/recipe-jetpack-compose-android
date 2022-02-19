package com.example.recipeappjetpackcomposelearn.network

import com.example.recipeappjetpackcomposelearn.domain.model.RecipeModel
import com.example.recipeappjetpackcomposelearn.domain.util.DomainMapper

class RecipeDtoMapper: DomainMapper<RecipeDto, RecipeModel> {

    override fun mapToDomainModel(entity: RecipeDto): RecipeModel {
        return RecipeModel(
            entity.pk,
            entity.title,
            entity.publisher,
            entity.featuredImage,
            entity.rating,
            entity.sourceUrl,
            entity.description,
            entity.cookingInstructions,
            entity.ingredients?: listOf(),
            entity.dateAdded,
            entity.dateUpdated,
            entity.longDateAdded,
            entity.longDateUpdated,
        )
    }

    override fun mapFromDomainModel(recipeModel: RecipeModel): RecipeDto {
        return RecipeDto(
            recipeModel.id,
            recipeModel.title,
            recipeModel.publisher,
            recipeModel.featuredImage,
            recipeModel.rating,
            recipeModel.sourceUrl,
            recipeModel.description,
            recipeModel.cookingInstructions,
            recipeModel.ingredients?: listOf(),
            recipeModel.dateAdded,
            recipeModel.dateUpdated,
            recipeModel.longDateAdded,
            recipeModel.longDateUpdated,
        )
    }

    fun toDomainModel(data: List<RecipeDto>): List<RecipeModel> {
        return data.map { mapToDomainModel(it) }
    }

    fun fromDomainModel(data: List<RecipeModel>): List<RecipeDto> {
        return data.map { mapFromDomainModel(it) }
    }

}