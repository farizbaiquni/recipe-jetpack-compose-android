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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp

@Composable
fun RecipeCategoryChip(
    isSelected: Boolean = false,
    category: String,
    onSelectedCategoryChange: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    context: Context = LocalContext.current,
){
    Surface(
        color = if (isSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary,
        modifier = Modifier.padding(top = 10.dp),
        shape = MaterialTheme.shapes.small,
    ) {
        Row(
            modifier = Modifier.toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectedCategoryChange(category)
                    onExecuteSearch()
                    // Toast.makeText(context, "Category ${category}, isSelected ${isSelected}", Toast.LENGTH_SHORT).show()
                }
            )

        ) {
            Text(
                text = category,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 7.dp, end = 7.dp),
            )
        }
    }
}