package com.flower.remote.utils

sealed class Resource<out T> {
    data class Success<T>(val data: T? = null, val source: DataSource = DataSource.REMOTE) : Resource<T>()
    data class Failure(val failureView: FailureView = FailureView.Dialog, val failureData: FailureData) :
        Resource<Nothing>()

    object Loading : Resource<Nothing>()
    object None : Resource<Nothing>()
}

enum class DataSource {
    CACHE,
    REMOTE
}

data class FailureData(val code: Int, val message: String? = null)

sealed class FailureView {
    object Toast : FailureView()
    object Dialog : FailureView()
    object Screen : FailureView()
}
