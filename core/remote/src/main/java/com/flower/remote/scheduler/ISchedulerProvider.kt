package com.flower.remote.scheduler

import kotlinx.coroutines.CoroutineDispatcher

interface ISchedulerProvider {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
}
