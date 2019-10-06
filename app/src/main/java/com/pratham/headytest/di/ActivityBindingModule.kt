package com.pratham.headytest.di

import com.pratham.headytest.ui.home.HomeActivity
import com.pratham.headytest.ui.splash.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun bindSplashScreenActivity(): SplashScreenActivity
}