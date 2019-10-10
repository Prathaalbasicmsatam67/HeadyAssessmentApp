package com.pratham.headytest.ui.model

data class CategoryUiModel(
    val localId: Long,
    val name: String?,
    val child_categories: List<String>?
)
