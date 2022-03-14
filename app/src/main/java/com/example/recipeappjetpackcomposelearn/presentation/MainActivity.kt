package com.example.recipeappjetpackcomposelearn.presentation

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeappjetpackcomposelearn.presentation.components.CircularIndeterminateProgressBar
import com.example.recipeappjetpackcomposelearn.presentation.components.RecipeCard
import com.example.recipeappjetpackcomposelearn.presentation.components.SearchAppBar
import com.example.recipeappjetpackcomposelearn.presentation.recipelist.PAGE_SIZE
import com.example.recipeappjetpackcomposelearn.presentation.recipelist.RecipeListViewModel
import com.example.recipeappjetpackcomposelearn.presentation.utils.ShimmerRecipeListAnimation
import com.example.recipeappjetpackcomposelearn.ui.theme.RecipeAppJetpackComposeLearnTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit @Named("auth_token") var random: String

    private val recipeListViewModel: RecipeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val recipes = recipeListViewModel.recipes.value
            val query = recipeListViewModel.query.value
            val scrollCategoryIndex = recipeListViewModel.scrollCategoryIndex.value
            val selectedCategory = recipeListViewModel.query.value
            val isLoading = recipeListViewModel.isLoading.value
            val isDarkMode = recipeListViewModel.isDarkMode.value
            val page = recipeListViewModel.page.value

            val categoryState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()

            RecipeAppJetpackComposeLearnTheme(darkTheme = isDarkMode) {

                this.window.statusBarColor = if(isDarkMode){
                    Color.parseColor("#121212")
                } else {
                    Color.parseColor("#087f23")
                }

                Scaffold(
                    Modifier
                        .background(MaterialTheme.colors.background)
                        .padding(horizontal = 10.dp),
                    topBar = {
                        //Search Top bar
                        SearchAppBar(
                            query = query,
                            onChangeQuery = recipeListViewModel::onChangeQuery,
                            onExecuteSearch = { recipeListViewModel.newQuery() },
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
                ) {
                    if(!isLoading || recipes.isNotEmpty()){
                        LazyColumn() {
                            itemsIndexed(
                                items = recipes,
                                key = { index, item -> item.id as Any }
                            ){
                                    index, recipe ->
                                recipeListViewModel.onChangeRecipeListPosition(index)
                                if((index + 1) >= (page * PAGE_SIZE)){
                                    recipeListViewModel.nextQuery()
                                }
                                RecipeCard(recipeModel = recipe, onClick = {} )
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
                        }
                    }
                }
            }
        }
    }
}