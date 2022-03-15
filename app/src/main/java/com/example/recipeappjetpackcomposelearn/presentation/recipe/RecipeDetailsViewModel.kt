package com.example.recipeappjetpackcomposelearn.presentation.recipe

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappjetpackcomposelearn.domain.model.RecipeModel
import com.example.recipeappjetpackcomposelearn.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor (

    private val recipeRepository: RecipeRepository,
    private @Named("auth_token") val authToken: String,

): ViewModel() {

    val idRecipe: MutableState<Int> = mutableStateOf(-1)
    val recipeDetails: MutableState<RecipeModel?> = mutableStateOf(null)
    val isLoading = mutableStateOf(true)

    fun queryRecipeDetails(idRecipe: Int){
        viewModelScope.launch {
            try {
                val result = recipeRepository.get(
                    token = authToken,
                    id = idRecipe,
                )
                recipeDetails.value = result
                isLoading.value = false
            } catch (e: Exception){}
        }
    }

    fun onSetIdRecipe(data: Int){
        this.idRecipe.value = data
    }

}