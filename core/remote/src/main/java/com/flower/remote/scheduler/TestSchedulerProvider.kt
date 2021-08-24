package com.flower.remote.scheduler

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class TestSchedulerProvider : ISchedulerProvider {
    override fun io() = TestCoroutineDispatcher()
    override fun ui() = TestCoroutineDispatcher()
    override fun default() = TestCoroutineDispatcher()
}
