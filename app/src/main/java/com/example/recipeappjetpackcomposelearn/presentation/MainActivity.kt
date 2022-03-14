package com.example.recipeappjetpackcomposelearn.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.recipeappjetpackcomposelearn.presentation.components.CircularIndeterminateProgressBar
import com.example.recipeappjetpackcomposelearn.presentation.components.RecipeCard
import com.example.recipeappjetpackcomposelearn.presentation.components.SearchAppBar
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

            val categoryState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()

            RecipeAppJetpackComposeLearnTheme(darkTheme = isDarkMode) {

                this.window.statusBarColor = if(isDarkMode){
                    Color.parseColor("#121212")
                } else {
                    Color.parseColor("#087f23")
                }

                Column(
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    //Search Top bar
                    SearchAppBar(
                        query = query,
                        onChangeQuery = recipeListViewModel::onChangeQuery,
                        onExecuteSearch = { recipeListViewModel.newSearch() },
                        categoryState = categoryState,
                        selectedCategory = selectedCategory,
                        onSelectedCategoryChange = recipeListViewModel::onSelectedCategoryChange,
                        onScrollCategory = recipeListViewModel::onScrollCategory,
                        coroutineScope = coroutineScope,
                        scrollCategoryIndex = scrollCategoryIndex,
                        isDarkMode = isDarkMode,
                        onChangeDarkMode = recipeListViewModel::onChangeDarkMode
                    )

                    Spacer(modifier = Modifier.padding(5.dp))

                    // Recipe List
                    Row() {
                        if(!isLoading){
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(3.dp),
                                modifier = Modifier.fillMaxWidth(0.95f)
                            ){
                                itemsIndexed(items = recipes){
                                        index, recipe ->
                                    RecipeCard(recipeModel = recipe, onClick = {} )
                                }
                            } // End LazyColumn
                        } else {
                            LazyColumn {
                                /*
                                  Lay down the Shimmer Animated item 5 time
                                  [repeat] is like a loop which executes the body
                                  according to the number specified
                                */
                                repeat(5) {
                                    item {
                                        // ShimmerRecipeListAnimation()
                                    }
                                }
                            }
                        }

                        CircularIndeterminateProgressBar(isDisplay = isLoading)

                    }

                }

            }
        }
    }
}