package com.example.recipeappjetpackcomposelearn.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.recipeappjetpackcomposelearn.ui.theme.DarkThemeBackground
import com.example.recipeappjetpackcomposelearn.ui.theme.Green500
import com.example.recipeappjetpackcomposelearn.ui.theme.Green700

@Composable
fun RecipeCategoryChip(
    isSelected: Boolean = false,
    category: String,
    onSelectedCategoryChange: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    isDarkMode: Boolean,
){
    Surface(
        color = backgroundColorCategoryChip(isDarkMode, isSelected),
        shape = MaterialTheme.shapes.small,
    ) {
        Row(
            modifier = Modifier.toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectedCategoryChange(category)
                    onExecuteSearch()
                },

            )

        ) {
            Text(
                text = category,
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp, start = 7.dp, end = 7.dp),
                fontFamily = MaterialTheme.typography.button.fontFamily,
                color = if(isDarkMode && isSelected) Color.Black else Color.White
            )
        }
    }
}

fun backgroundColorCategoryChip(isDarkMode: Boolean, isSelected: Boolean): Color{
    if(isDarkMode){
        if(isSelected){
            return Color.LightGray
        }else{
            return DarkThemeBackground
        }
    }else{
        if(isSelected){
            return Green500
        }else{
            return Green700
        }
    }
}