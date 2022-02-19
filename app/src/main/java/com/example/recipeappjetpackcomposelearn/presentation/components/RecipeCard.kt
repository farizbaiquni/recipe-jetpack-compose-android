package com.example.recipeappjetpackcomposelearn.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.recipeappjetpackcomposelearn.R
import com.example.recipeappjetpackcomposelearn.domain.model.RecipeModel

@Composable
fun RecipeCard(
    recipeModel: RecipeModel,
    onClick: () -> Unit,
){
    Card(
        shape = MaterialTheme.shapes.small,
        elevation = 8.dp,
        modifier = Modifier
            .padding(bottom = 6.dp, top = 6.dp)
            .fillMaxWidth()
            .clickable { onClick },
    ) {
        Column() {
            recipeModel.featuredImage?.let{ url -> 
                Image(
                    painterResource(id = R.drawable.empty_plate),
                    contentDescription = "no image recipe",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                )
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
                            modifier = Modifier.fillMaxWidth()
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