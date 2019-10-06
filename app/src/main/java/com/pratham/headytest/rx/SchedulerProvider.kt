package com.pratham.headytest.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun provideSchedulerProvider(): Scheduler
}