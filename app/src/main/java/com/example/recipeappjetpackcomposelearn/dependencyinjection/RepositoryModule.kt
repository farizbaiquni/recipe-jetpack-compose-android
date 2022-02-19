package com.example.recipeappjetpackcomposelearn.dependencyinjection

import com.example.recipeappjetpackcomposelearn.domain.repository.RecipeRepository
import com.example.recipeappjetpackcomposelearn.domain.repository.RecipeRepository_Impl
import com.example.recipeappjetpackcomposelearn.network.RecipeDtoMapper
import com.example.recipeappjetpackcomposelearn.network.response.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepository_Impl(recipeService, recipeDtoMapper)
    }

}