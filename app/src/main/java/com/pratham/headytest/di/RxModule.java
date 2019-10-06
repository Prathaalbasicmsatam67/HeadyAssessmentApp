package com.pratham.headytest.di;


import com.pratham.headytest.rx.IoThreadScheduler;
import com.pratham.headytest.rx.IoThreadSchedulerProvider;
import com.pratham.headytest.rx.MainThreadScheduler;
import com.pratham.headytest.rx.MainThreadSchedulerProvider;
import com.pratham.headytest.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class RxModule {

    @Provides
    @Singleton
    @MainThreadScheduler
    static SchedulerProvider provideMainThreadSchedulerProvider() {
        return new MainThreadSchedulerProvider();
    }

    @Provides
    @Singleton
    @IoThreadScheduler
    static SchedulerProvider provideIoSchedulerProvider() {
        return new IoThreadSchedulerProvider();
    }

    @Provides
    static CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}