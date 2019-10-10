package com.pratham.headytest.db.dao

import androidx.room.*
import com.pratham.headytest.db.model.JoinProductVariantTable
import com.pratham.headytest.db.tables.ProductTable
import io.reactivex.Flowable

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun allProducts(): Flowable<List<ProductTable>>


    @Query(
        "SELECT \n" +
                "`product`.`id` as `productId`,\n" +
                "`name` as `productName`, \n" +
                "`server_id` as `productServerId`,\n" +
                " variant.`id` as `variantId`,\n" +
                "`color` as `variantColor`,\n" +
                "`size` as `variantSize`,\n" +
                "`price` as `variantPrice`\n" +
                "  FROM `product` as product\n" +
                "  LEFT JOIN product_variant_table as variant\n" +
                "  ON variant.productId = product.id"
    )
    fun getAllProductWithVariants(): Flowable<List<JoinProductVariantTable>>


    @Query(
        "SELECT\n" +
                "                `product`.`id` as `productId`,\n" +
                "                `name` as `productName`,\n" +
                "                `server_id` as `productServerId`,\n" +
                "                 variant.`id` as `variantId`,\n" +
                "                `color` as `variantColor`,\n" +
                "                `size` as `variantSize`,\n" +
                "                `price` as `variantPrice`\n" +
                "                  FROM `product` as product \n" +
                "                  LEFT JOIN product_variant_table as variant\n" +
                "                  ON variant.productId = product.id where product.categoryId =:categoryId"
    )
    fun getAllProductWithVariantsForCategory(categoryId: Long?): Flowable<List<JoinProductVariantTable>>


    @Query(
        "SELECT\n" +
                "                `product`.`id` as `productId`,\n" +
                "                `name` as `productName`,\n" +
                "                `server_id` as `productServerId`,\n" +
                "                 variant.`id` as `variantId`,\n" +
                "                `color` as `variantColor`,\n" +
                "                `size` as `variantSize`,\n" +
                "                `price` as `variantPrice`\n" +
                "                  FROM `product` as product\n" +
                "                  LEFT JOIN product_variant_table as variant\n" +
                "                  ON variant.productId = product.id where product.server_id in " +
                "(select productServerId from product_ranking_table where ranking_id=:rankingId)"
    )
    fun getAllProductWithVariantsForRanking(rankingId: Int?): Flowable<List<JoinProductVariantTable>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: ProductTable): Long

    @Delete
    suspend fun remove(product: ProductTable)

    @Query("SELECT id from product")
    suspend fun getProductIds(): List<Long>

    @Query("DELETE FROM product")
    suspend fun clear()
}