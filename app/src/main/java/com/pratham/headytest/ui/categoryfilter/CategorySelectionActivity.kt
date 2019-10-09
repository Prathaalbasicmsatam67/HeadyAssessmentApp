package com.pratham.headytest.ui.categoryfilter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pratham.headytest.R
import com.pratham.headytest.app.BaseView
import com.pratham.headytest.ui.categoryfilter.adapter.CategorySelectionAdapter
import com.pratham.headytest.ui.categoryfilter.adapter.OnViewClickListener
import com.pratham.headytest.ui.categoryfilter.model.CategoryUiModel
import com.pratham.headytest.ui.home.HomeActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class CategorySelectionActivity : BaseView<CategoryViewModel>, DaggerAppCompatActivity(),
    OnViewClickListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: CategoryViewModel

    lateinit var btnApply: Button
    lateinit var recyclerView: RecyclerView

    private lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var selectedCategoryUiModel: CategoryUiModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_selection)
        btnApply = findViewById(R.id.btnApply)
        recyclerView = findViewById(R.id.recyclerCat)
        viewModel = getAssociatedViewModel()

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager




        viewModel.getAllCatLiveData.observe(this, Observer { list ->
            Log.d("FilterActivity", "Cat List " + list.toString())
            // set data to recycler view
            recyclerView.adapter = CategorySelectionAdapter(this, list, this)
        })

        btnApply.setOnClickListener {

            val intent = Intent()
            intent.putExtra(HomeActivity.CATEGORY_DATA, selectedCategoryUiModel)
            setResult(HomeActivity.CATEGORY_DATA_CODE, intent)
            finish()//finishing activity
        }

        viewModel.getAllDataList()
    }

    override fun selectedItem(category: CategoryUiModel) {
        Toast.makeText(this, "Selected category : " + category.categoryName, Toast.LENGTH_LONG)
            .show()

        selectedCategoryUiModel = category
    }

    override fun getAssociatedViewModel(): CategoryViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(CategoryViewModel::class.java)
    }
}
