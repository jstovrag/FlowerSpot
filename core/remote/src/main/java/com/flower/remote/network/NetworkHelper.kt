package com.flower.remote.network

import com.flower.remote.utils.ErrorCodesMapper
import com.flower.remote.utils.NetworkCodes
import com.flower.remote.utils.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    errorCodesMapper: ErrorCodesMapper,
    apiCall: suspend () -> T?
): ResultWrapper<T?> {
    return withContext(dispatcher) {
        try {
            val response = apiCall.invoke()
            if (response !is Response<*>)
                ResultWrapper.Success(response)
            else {
                if (response.isSuccessful)
                    ResultWrapper.Success(response)
                else {
                    val code = response.code()
                    ResultWrapper.Failure(
                        code = code,
                        message = errorCodesMapper.getMessage(code)
                    )
                }
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    ResultWrapper.Failure(
                        code = NetworkCodes.TIMEOUT_ERROR,
                        message = errorCodesMapper.getMessage(NetworkCodes.CONNECTION_ERROR)
                    )
                }
                is IOException -> {
                    ResultWrapper.Failure(
                        code = NetworkCodes.CONNECTION_ERROR,
                        message = errorCodesMapper.getMessage(NetworkCodes.CONNECTION_ERROR)
                    )
                }
                is HttpException -> {
                    val code = throwable.code()
                    ResultWrapper.Failure(
                        code = code,
                        message = errorCodesMapper.getMessage((throwable.code()))
                    )
                }
                else -> {
                    ResultWrapper.Failure(
                        code = NetworkCodes.GENERIC_ERROR,
                        message = errorCodesMapper.getMessage(NetworkCodes.GENERIC_ERROR)
                    )
                }
            }
        }
    }
}
