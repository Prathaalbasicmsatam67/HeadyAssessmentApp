package com.pratham.headytest.db.model

data class JoinProductVariantTable(
    val productId: Long,
    val productName: String,
    val productServerId: Int,
    val variantId: Long,
    val variantColor: String,
    val variantSize: Int,
    val variantPrice: Int
)