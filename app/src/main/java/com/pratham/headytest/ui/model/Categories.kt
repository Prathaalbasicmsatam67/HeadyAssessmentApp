package com.pratham.headytest.ui.model

data class Categories(

    val id: Int,
    val name: String,
    val products: List<CategoryProduct>,
    val child_categories: List<String>
)