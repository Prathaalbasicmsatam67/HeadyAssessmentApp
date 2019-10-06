package com.pratham.headytest.di

import com.pratham.headytest.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindHomeActivity(): HomeActivity
}