package com.pratham.headytest.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class MainThreadSchedulerProvider : SchedulerProvider {
    override fun provideSchedulerProvider(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}