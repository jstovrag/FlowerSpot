package com.flower.remote.scheduler

import kotlinx.coroutines.Dispatchers

class SchedulerProvider : ISchedulerProvider {
    override fun io() = Dispatchers.IO
    override fun ui() = Dispatchers.Main
    override fun default() = Dispatchers.Default
}
