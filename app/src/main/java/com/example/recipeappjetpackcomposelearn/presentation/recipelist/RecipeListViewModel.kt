package com.example.recipeappjetpackcomposelearn.presentation.recipelist

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
class RecipeListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private @Named("auth_token") val authToken: String
): ViewModel() {

    val recipes: MutableState<List<RecipeModel>> = mutableStateOf(listOf())

    init {
        newSearch()
    }

    fun newSearch(){
        viewModelScope.launch {
            var result = recipeRepository.search("Token 9c8b06d329136da358c2d00e76946b0111ce2c48", 1, "chicken")
            recipes.value = result
        }
    }
}