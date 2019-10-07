package com.pratham.headytest.ui.model

data class ProductUiModel(
    val id: Long?,
    val name: String,
    val server_id: Int,
    val variants: List<Variants>?,
    val ranking: String?,
    val viewCount: Int?,
    val category: CategoryUiModel?
);