package com.pratham.headytest.ui.home.model

data class Categories(

    val id: Int,
    val name: String,
    val products: List<Products>,
    val child_categories: List<String>
)