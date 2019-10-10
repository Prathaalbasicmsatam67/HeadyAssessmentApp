package com.pratham.headytest.ui.model

import java.util.*

data class ProductUiModel(
    val id: Long?,
    val name: String,
    val server_id: Int,
    val variants: ArrayList<Variants>?
);