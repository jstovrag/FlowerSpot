package com.flower.remote.utils

import android.content.Context
import com.flower.remote.R
import javax.inject.Inject

class ErrorCodesMapper @Inject constructor(private val context: Context) {
    fun getMessage(errorCode: Int) = when (errorCode) {
        NetworkCodes.CONNECTION_ERROR,
        NetworkCodes.TIMEOUT_ERROR -> context.getString(R.string.connection_time_out)
        else -> context.getString(R.string.generic_error)
    }
}
