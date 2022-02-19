package com.example.recipeappjetpackcomposelearn.network.response

import com.example.recipeappjetpackcomposelearn.network.RecipeDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeService {

    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String,
    ): RecipeResponses

    @GET("get")
    suspend fun get(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): RecipeDto

}