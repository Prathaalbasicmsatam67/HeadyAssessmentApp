package com.pratham.headytest.di

import androidx.lifecycle.ViewModelProvider
import com.pratham.headytest.app.BaseViewModel
import com.pratham.headytest.ui.categoryfilter.CategoryViewModel
import com.pratham.headytest.ui.home.HomeViewModel
import com.pratham.headytest.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    protected abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): BaseViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    protected abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): BaseViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    protected abstract fun bindCategoryViewModel(categoryViewModel: CategoryViewModel): BaseViewModel
}