package com.pratham.headytest.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.pratham.headytest.R
import com.pratham.headytest.app.BaseView
import com.pratham.headytest.ui.categoryfilter.CategorySelectionActivity
import com.pratham.headytest.ui.categoryfilter.model.CategoryUiModel
import com.pratham.headytest.ui.home.adapter.CustomArrayAdapter
import com.pratham.headytest.ui.home.model.RankingUiModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class HomeActivity : BaseView<HomeViewModel>, DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: HomeViewModel

    private lateinit var btnFilter: Button
    private lateinit var spnRanking: Spinner
    private lateinit var recyclerView: RecyclerView

    companion object {
        val CATEGORY_DATA_CODE = 1000
        val CATEGORY_DATA = "DATA"
    }

    private lateinit var selectedCategory: CategoryUiModel
    private lateinit var selectedRanking: RankingUiModel

    override fun getAssociatedViewModel(): HomeViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnFilter = findViewById(R.id.btnFilter)
        spnRanking = findViewById(R.id.spnRanking)
        recyclerView = findViewById(R.id.recyclerView)

        viewModel = getAssociatedViewModel()

        viewModel.getAllRanking.observe(this, Observer { data ->

            // set ranking data
            val adapter = CustomArrayAdapter(
                applicationContext,
                R.layout.spinner_item, data
            )

            spnRanking.adapter = adapter

            spnRanking?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedRanking = data[position]

                }
            }
        })


        viewModel.getAllProducts.observe(this, Observer {
            Log.d("class", "productList : " + it.toString())
        })



        btnFilter.setOnClickListener {
            startActivityForResult(
                Intent(this, CategorySelectionActivity::class.java),
                CATEGORY_DATA_CODE
            )
        }

        // call to get all rankings
        viewModel.getAllRanking()

        // call to get all products
        viewModel.getAllProducts()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CATEGORY_DATA_CODE) {
            if (data != null && data.hasExtra(CATEGORY_DATA)) {
                selectedCategory = data.getParcelableExtra(CATEGORY_DATA)
                Toast.makeText(
                    this,
                    "Selected category : " + selectedCategory?.categoryName,
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }

    }


//    private boolean loading = true;
//    int pastVisiblesItems, visibleItemCount, totalItemCount;
//
//    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
//    {
//        @Override
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy)
//        {
//            if(dy > 0) //check for scroll down
//            {
//                visibleItemCount = mLayoutManager.getChildCount();
//                totalItemCount = mLayoutManager.getItemCount();
//                pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
//
//                if (loading)
//                {
//                    if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
//                    {
//                        loading = false;
//                        Log.v("...", "Last Item Wow !");
//                        //Do pagination.. i.e. fetch new data
//                    }
//                }
//            }
//        }
//    });


}


