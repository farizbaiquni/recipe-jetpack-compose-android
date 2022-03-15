package com.example.recipeappjetpackcomposelearn.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeappjetpackcomposelearn.presentation.recipelist.getAllFoodCategories
import com.example.recipeappjetpackcomposelearn.presentation.utils.ShimmerFoodCategoryAnimation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(
    query: String,
    onChangeQuery: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    categoryState: LazyListState,
    selectedCategory: String,
    onSelectedCategoryChange: (String) -> Unit,
    onScrollCategory: (Int) -> Unit,
    coroutineScope: CoroutineScope,
    scrollCategoryIndex: Int,
    isDarkMode: Boolean,
    onChangeDarkMode: () -> Unit,
    recipesSize: Int,
    isLoading: Boolean,

){
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Surface(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
    ) {
        Column() {
            Row( ){
                TextField(
                    value = query,
                    onValueChange = {
                        onChangeQuery(it)
                    },
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Search")
                    },
                    leadingIcon = {
                        Icon(Icons.Filled.Search, "Search")
                    },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.MoreVert,
                            "Dark Mode",
                            Modifier.clickable {
                                onChangeDarkMode()
                            }
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search,
                    ),
                    keyboardActions = KeyboardActions( onSearch = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        onExecuteSearch()
                    }),

                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background,
                    )
                )
            }

            Row( modifier = Modifier.padding(top = 10.dp) ) {
                if(isLoading && recipesSize <= 0){
                    LazyRow(){
                        repeat(10){
                            item {
                                ShimmerFoodCategoryAnimation()
                            }
                        }
                    }
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        state = categoryState,
                    ){
                        itemsIndexed(items = getAllFoodCategories()){
                                index, item ->
                            RecipeCategoryChip(
                                category = item.value,
                                isSelected = selectedCategory === item.value,
                                onSelectedCategoryChange = {
                                    onSelectedCategoryChange(it)
                                    onScrollCategory(index)
                                },
                                onExecuteSearch = {
                                    onExecuteSearch()
                                },
                                isDarkMode = isDarkMode,
                            )
                        }
                        coroutineScope.launch {
                            categoryState.scrollToItem(scrollCategoryIndex)
                        }
                    }
                }
            }

        }
    } // End Surface
}