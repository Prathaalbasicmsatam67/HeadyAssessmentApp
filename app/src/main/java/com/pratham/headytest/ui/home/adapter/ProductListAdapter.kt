package com.pratham.headytest.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pratham.headytest.R
import com.pratham.headytest.ui.home.HomeActivity
import com.pratham.headytest.ui.model.ProductUiModel
import kotlinx.android.synthetic.main.list_item_category_selection.view.txtName
import kotlinx.android.synthetic.main.list_item_product.view.*


class ProductListAdapter(
    private val context: HomeActivity,
    private val productList: List<ProductUiModel?>
) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item_product,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName?.text = productList.get(position)?.name
        holder.variantDetails.text = productList.get(position)?.variants.toString()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName = view.txtName
        val variantDetails = view.txtVariantDetails
    }
}