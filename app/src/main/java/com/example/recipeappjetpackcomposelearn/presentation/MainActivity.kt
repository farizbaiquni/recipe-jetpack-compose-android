package com.example.recipeappjetpackcomposelearn.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeappjetpackcomposelearn.network.response.RecipeService
import com.example.recipeappjetpackcomposelearn.presentation.components.RecipeCard
import com.example.recipeappjetpackcomposelearn.presentation.recipelist.RecipeListViewModel
import com.example.recipeappjetpackcomposelearn.ui.theme.RecipeAppJetpackComposeLearnTheme
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit @Named("auth_token") var random: String

    private val recipeListViewModel: RecipeListViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val recipes = recipeListViewModel.recipes.value
            val keyboardController = LocalSoftwareKeyboardController.current
            val focusManager = LocalFocusManager.current

            RecipeAppJetpackComposeLearnTheme {

                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Surface(
                        modifier = Modifier.fillMaxWidth(0.95f),
                        elevation = 30.dp,
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ){
                            TextField(
                                value = recipeListViewModel.query.value,
                                onValueChange = { newQuery ->
                                    recipeListViewModel.changeQuery(newQuery)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                label = {
                                        Text("Search")
                                },
                                leadingIcon = {
                                    Icon(Icons.Filled.Search, "Search")
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Search,
                                ),
                                keyboardActions = KeyboardActions( onSearch = {
                                    keyboardController?.hide()
                                    focusManager.clearFocus()
                                    recipeListViewModel.newSearch(recipeListViewModel.query.value)
                                }),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.padding(4.dp))

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(3.dp),
                        modifier = Modifier.fillMaxWidth(0.95f)
                    ){
                        itemsIndexed(items = recipes){
                                index, recipe ->
                            RecipeCard(recipeModel = recipe, onClick = {} )
                        }
                    }
                }
            }
        }
    }
}