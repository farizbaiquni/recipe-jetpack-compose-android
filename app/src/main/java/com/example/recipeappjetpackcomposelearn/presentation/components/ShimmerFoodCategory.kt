package com.example.recipeappjetpackcomposelearn.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerFoodCategory(
    brush: Brush
) {
    // Column composable containing spacer shaped like a rectangle,
    // set the [background]'s [brush] with the brush receiving from [ShimmerAnimation]
    // Composable which is the Animation you are gonna create.
    Row(modifier = Modifier.padding(end = 10.dp)) {
        Spacer(
            modifier = Modifier
                .width(70.dp)
                .height(25.dp)
                .background(brush = brush)
        )
    }
}