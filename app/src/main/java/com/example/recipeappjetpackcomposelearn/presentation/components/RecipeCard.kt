package com.example.recipeappjetpackcomposelearn.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.recipeappjetpackcomposelearn.R
import com.example.recipeappjetpackcomposelearn.domain.model.RecipeModel
import com.example.recipeappjetpackcomposelearn.util.LoadImage

@ExperimentalMaterialApi
@Composable
fun RecipeCard(
    recipeModel: RecipeModel,
    onClickRecipeCard: () -> Unit,
){
    Card(
        shape = MaterialTheme.shapes.small,
        elevation = 8.dp,
        modifier = Modifier
            .padding(bottom = 6.dp, top = 6.dp),
        onClick = onClickRecipeCard
    ) {
        Column() {
            recipeModel.featuredImage?.let{ url ->

                var image = LoadImage(url = url, defaultImage = R.drawable.empty_plate).value

                image?.let { image ->
                    Image(
                        bitmap = image.asImageBitmap(),
                        contentDescription = "no image recipe",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                    )
                }

            }
            recipeModel.title?.let { title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h5
                    )

                    recipeModel.rating?.let { rating ->
                        Text(
                            text = rating.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.End)
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
        } // end column
    }
}