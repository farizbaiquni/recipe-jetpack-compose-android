package com.example.recipeappjetpackcomposelearn.presentation.recipelist

enum class FoodCategory(var value: String) {
    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    MILK("Milk"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donut")
}

fun getAllFoodCategories(): List<FoodCategory>{
    return listOf<FoodCategory>(FoodCategory.CHICKEN, FoodCategory.BEEF, FoodCategory.SOUP,
        FoodCategory.DESSERT, FoodCategory.VEGETARIAN, FoodCategory.MILK, FoodCategory.VEGAN,
        FoodCategory.PIZZA, FoodCategory.DONUT)
}

fun getCategoryChip(value: String): FoodCategory?{
    var map = FoodCategory.values().associateBy(FoodCategory::value)
    return map[value]
}