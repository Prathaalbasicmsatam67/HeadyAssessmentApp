package com.pratham.headytest.ui.model

data class CategoryProduct(

    val id: Int,
    val name: String,
    val date_added: String,
    val variants: List<Variants>,
    val tax: Tax?
)