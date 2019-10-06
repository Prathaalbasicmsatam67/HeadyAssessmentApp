package com.pratham.headytest.di

import androidx.lifecycle.ViewModelProvider
import com.pratham.headytest.app.BaseViewModel
import com.pratham.headytest.ui.home.HomeViewModel
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
    protected abstract fun provideHomeViewModel(homeViewModel: HomeViewModel): BaseViewModel
}