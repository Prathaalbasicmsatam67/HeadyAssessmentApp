package com.pratham.headytest.ui.categoryfilter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pratham.headytest.R
import com.pratham.headytest.ui.categoryfilter.CategorySelectionActivity
import com.pratham.headytest.ui.categoryfilter.model.CategoryUiModel
import kotlinx.android.synthetic.main.list_item_category_selection.view.*

class CategorySelectionAdapter(
    private val context: CategorySelectionActivity,
    private val categoryList: List<CategoryUiModel>,
    private val listener: OnViewClickListener
) :
    RecyclerView.Adapter<CategorySelectionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item_category_selection,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName?.text = categoryList.get(position).categoryName
        holder.itemView.setOnClickListener {
            listener.selectedItem(categoryList.get(position))
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryName = view.txtName
    }
}