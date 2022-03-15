package com.example.recipeappjetpackcomposelearn.presentation.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeappjetpackcomposelearn.R
import com.example.recipeappjetpackcomposelearn.presentation.components.CircularIndeterminateProgressBar
import com.example.recipeappjetpackcomposelearn.util.LoadImage

@Composable
fun RecipeDetails(idRecipe: String){

    val recipeDetailsViewModel: RecipeDetailsViewModel = hiltViewModel()
    val recipeDetails = recipeDetailsViewModel.recipeDetails.value
    val isLoading = recipeDetailsViewModel.isLoading.value

    var scrollState = rememberScrollState()

    // Fetch recipe details
    recipeDetailsViewModel.queryRecipeDetails(idRecipe.toInt())

    Scaffold() {

        if(!isLoading){
            if (recipeDetails != null){
                Column(
                    modifier = Modifier.verticalScroll( scrollState,true).padding(bottom = 15.dp)
                ){
                    var imageRecipeBitmap = LoadImage(
                        url = recipeDetails?.featuredImage.toString(),
                        defaultImage = R.drawable.empty_plate
                    ).value

                    imageRecipeBitmap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "recipe-image",
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Row(Modifier.padding(vertical = 5.dp)) {
                            Text(text = recipeDetails.title.toString(), style = MaterialTheme.typography.h5)
                            Text(text = recipeDetails.rating.toString(), style = MaterialTheme.typography.h5)
                        }

                        Row() {
                            Text(text = "Updated ", fontSize = 13.sp)
                            Text(text = recipeDetails.dateUpdated.toString(), fontSize = 13.sp)
                            Text(text = " by ", fontSize = 13.sp)
                            Text(text = recipeDetails.publisher.toString(), fontSize = 13.sp)
                        }

                        Spacer(modifier = Modifier.padding(10.dp))

                        for (item in recipeDetails.ingredients){
                            Text(text = " - ${item}")
                        }
                    }

                }// End Column
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularIndeterminateProgressBar(isDisplay = isLoading)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposableReview(){
    RecipeDetails(idRecipe = "2")
}