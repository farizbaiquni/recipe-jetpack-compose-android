package com.example.recipeappjetpackcomposelearn.presentation.recipelist

import android.util.Log
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

const val PAGE_SIZE = 30

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
    val page = mutableStateOf(1)

    var scrollRecipeListPosition = 0;

    init {
        onTriggerRecipeListEvent(RecipeListEvent.NewQueryEvent)
    }

    fun onTriggerRecipeListEvent(event: RecipeListEvent){
        viewModelScope.launch {
            try {
                when(event) {
                    is RecipeListEvent.NewQueryEvent -> {
                        newQuery()
                    }
                    is RecipeListEvent.NextQueryEvent -> {
                        nextQuery()
                    }
                }
            } catch (e: Exception){}
        }
    }


    suspend fun newQuery(){
        recipes.value = arrayListOf()
        isLoading.value = true
        var result = recipeRepository.search(
            authToken,
            1,
            query.value)
        recipes.value = result
        isLoading.value = false
    }


    suspend fun nextQuery(){
        if((scrollRecipeListPosition + 1) >= (page.value * PAGE_SIZE)){
            isLoading.value = true
            incrementPage()

            val result = recipeRepository.search(
                token = authToken,
                page = page.value,
                query = query.value,
            )

            appendRecipes(result)
            isLoading.value = false
        }
    }


    fun appendRecipes(data: List<RecipeModel>){
        val currentRecipes = ArrayList(this.recipes.value)
        currentRecipes.addAll(data)
        this.recipes.value = currentRecipes
    }


    fun incrementPage(){
        this.page.value = this.page.value + 1
    }

    fun onChangeRecipeListPosition(value: Int){
        scrollRecipeListPosition = value
    }

    fun onChangeDarkMode(value: Boolean){
        this.isDarkMode.value = value
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