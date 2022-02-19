package com.example.recipeappjetpackcomposelearn.domain.model

data class RecipeModel(
    val id: Int? = null,
    val title: String? = null,
    val publisher: String? = null,
    val featuredImage: String? = null,
    val rating: Int? = null,
    val sourceUrl: String? = null,
    val description: String? = null,
    val cookingInstructions: String? = null,
    val ingredients: List<String> = listOf(),
    val dateAdded: String? = null,
    val dateUpdated: String? = null,
    val longDateAdded: Long? = null,
    val longDateUpdated: Long? = null,
)
