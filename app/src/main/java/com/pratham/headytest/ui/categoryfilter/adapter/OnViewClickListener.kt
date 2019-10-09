package com.pratham.headytest.ui.categoryfilter.adapter

import com.pratham.headytest.ui.categoryfilter.model.CategoryUiModel

interface OnViewClickListener {
    fun selectedItem(category: CategoryUiModel)
}