package com.example.recipeappjetpackcomposelearn.presentation.recipelist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappjetpackcomposelearn.domain.model.RecipeModel
import com.example.recipeappjetpackcomposelearn.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private @Named("auth_token") val authToken: String
): ViewModel() {

    val recipes: MutableState<List<RecipeModel>> = mutableStateOf(listOf())
    val query: MutableState<String> = mutableStateOf("")
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    val scrollCategoryIndex: MutableState<Int> = mutableStateOf(0)
    val isLoading = mutableStateOf(false)
    val isDarkMode = mutableStateOf(false)

    init {
        newSearch()
    }

    fun onChangeDarkMode(value: Boolean){
        this.isDarkMode.value = value
    }

    fun newSearch(){
        viewModelScope.launch {
            isLoading.value = true
            delay(2000)
            var result = recipeRepository.search(
                "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
                1,
                query.value)
            recipes.value = result
            isLoading.value = false
        }
    }

    fun onChangeQuery(input: String){
        this.query.value = input
    }

    fun onSelectedCategoryChange(category: String){
        val newCategory = getCategoryChip(category)
        selectedCategory.value = newCategory
        onChangeQuery(category)
    }

    fun onScrollCategory(index: Int){
        this.scrollCategoryIndex.value = index
    }

}