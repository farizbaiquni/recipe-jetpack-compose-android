package com.example.recipeappjetpackcomposelearn.presentation.recipelist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.recipeappjetpackcomposelearn.presentation.components.CircularIndeterminateProgressBar
import com.example.recipeappjetpackcomposelearn.presentation.components.RecipeCard
import com.example.recipeappjetpackcomposelearn.presentation.components.SearchAppBar
import com.example.recipeappjetpackcomposelearn.presentation.recipe.RecipeDetailsViewModel
import com.example.recipeappjetpackcomposelearn.presentation.utils.ShimmerRecipeListAnimation
import com.example.recipeappjetpackcomposelearn.util.Screen


@ExperimentalMaterialApi
@Composable
fun RecipeList(
    navController: NavHostController,
    recipeListViewModel: RecipeListViewModel
){
    val recipes = recipeListViewModel.recipes.value
    val query = recipeListViewModel.query.value
    val scrollCategoryIndex = recipeListViewModel.scrollCategoryIndex.value
    val selectedCategory = recipeListViewModel.query.value
    val isLoading = recipeListViewModel.isLoading.value
    val page = recipeListViewModel.page.value
    val isDarkMode = recipeListViewModel.isDarkMode.value

    val categoryState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        Modifier
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 10.dp),
        topBar = {
            Column() {
                //Search Top bar
                SearchAppBar(
                    query = query,
                    onChangeQuery = recipeListViewModel::onChangeQuery,
                    onExecuteSearch = { recipeListViewModel.onTriggerRecipeListEvent(RecipeListEvent.NewQueryEvent) },
                    categoryState = categoryState,
                    selectedCategory = selectedCategory,
                    onSelectedCategoryChange = recipeListViewModel::onSelectedCategoryChange,
                    onScrollCategory = recipeListViewModel::onScrollCategory,
                    coroutineScope = coroutineScope,
                    scrollCategoryIndex = scrollCategoryIndex,
                    isDarkMode = isDarkMode,
                    onChangeDarkMode = recipeListViewModel::onChangeDarkMode,
                    recipesSize = recipes.size,
                    isLoading = isLoading,
                )
            }
        }
    ) {
        if(!isLoading || recipes.isNotEmpty()){

            LazyColumn() {
                itemsIndexed(
                    items = recipes,
                    key = { _, item -> item.id as Any }
                ){
                        index, recipe ->
                    recipeListViewModel.onChangeRecipeListPosition(index)
                    if((index + 1) >= (page * PAGE_SIZE)){
                        recipeListViewModel.onTriggerRecipeListEvent(RecipeListEvent.NextQueryEvent)
                    }
                    RecipeCard(
                        recipeModel = recipe,
                        onClickRecipeCard = {
                            recipe.id?.let {
                                moveToRecipeDetails(navController, recipe.id.toString())
                            }
                        }
                    )
                }
            } // End LazyColumn

        } else {

            Column() {
                CircularIndeterminateProgressBar(isDisplay = isLoading)
                LazyColumn {
                    repeat(5) {
                        item {
                            ShimmerRecipeListAnimation()
                        }
                    }
                }
            } // End Column

        }
    } // End Scaffold
}

fun moveToRecipeDetails(navController: NavHostController, idRecipe: String){
    navController.navigate(Screen.RecipeDetails.route + "/${idRecipe}")
}