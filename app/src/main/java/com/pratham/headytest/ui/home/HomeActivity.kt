package com.pratham.headytest.ui.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pratham.headytest.R
import com.pratham.headytest.app.BaseView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomeActivity : BaseView<HomeViewModel>, DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: HomeViewModel


    override fun getAssociatedViewModel(): HomeViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel = getAssociatedViewModel()

        viewModel.getAllDataLiveData.observe(this, Observer { data ->
            Log.d("class", "data : " + data.toString())
        })

    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllDataList();
    }
}