package com.flower.remote.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.flower.remote.BuildConfig
import com.flower.remote.environment.EnvironmentConfig
import com.flower.remote.environment.IEnvironmentConfig
import com.flower.remote.network.AuthInterceptor
import com.flower.remote.network.RequestHeadersInterceptor
import com.flower.remote.scheduler.ISchedulerProvider
import com.flower.remote.scheduler.SchedulerProvider
import com.flower.remote.utils.ErrorCodesMapper
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Qualifier
annotation class IsDebugBuild

@Qualifier
annotation class LoggerInterceptorProvider

@Qualifier
annotation class AuthInterceptorProvider

@Qualifier
annotation class RequestHeadersInterceptorProvider

@Qualifier
annotation class AuthOkHttpClient

@Qualifier
annotation class NormalOkHttpClient

@Qualifier
annotation class AuthRetrofitClient

@Qualifier
annotation class NormalRetrofitClient

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @IsDebugBuild
    fun provideIsDebugBuild(): Boolean = BuildConfig.DEBUG

    @Provides
    fun provideEnvironmentConfig(
        @IsDebugBuild isDebugBuild: Boolean,
    ): IEnvironmentConfig {
        return if (isDebugBuild) EnvironmentConfig.Development else EnvironmentConfig.Production
    }

    @LoggerInterceptorProvider
    @Provides
    fun provideLoggingInterceptor(iEnvironmentConfig: IEnvironmentConfig): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(iEnvironmentConfig.logLevel())
    }

    @AuthInterceptorProvider
    @Provides
    fun provideAuthInterceptor(): Interceptor {
        return AuthInterceptor()
    }

    @RequestHeadersInterceptorProvider
    @Provides
    fun provideRequestHeadersInterceptor(): Interceptor {
        return RequestHeadersInterceptor()
    }

    @Provides
    fun provideErrorMapper(@ApplicationContext appContext: Context): ErrorCodesMapper {
        return ErrorCodesMapper(appContext)
    }

    @NormalOkHttpClient
    @Provides
    fun provideNormalOkHttpClient(
        @LoggerInterceptorProvider loggerInterceptor: Interceptor,
        @RequestHeadersInterceptorProvider requestHeadersInterceptor: Interceptor
    ): OkHttpClient {
        return clientBuilder(
            loggerInterceptor = loggerInterceptor,
            requestHeadersInterceptor = requestHeadersInterceptor
        )
    }

    @AuthOkHttpClient
    @Provides
    fun provideAuthOkHttpClient(
        @LoggerInterceptorProvider loggerInterceptor: Interceptor,
        @AuthInterceptorProvider authInterceptor: Interceptor,
        @RequestHeadersInterceptorProvider requestHeadersInterceptor: Interceptor
    ): OkHttpClient {
        return clientBuilder(
            loggerInterceptor = loggerInterceptor,
            authInterceptor = authInterceptor,
            requestHeadersInterceptor = requestHeadersInterceptor
        )
    }

    @NormalRetrofitClient
    @Provides
    fun provideNormalRetrofitClient(
        @NormalOkHttpClient okHttpClient: OkHttpClient,
        environmentConfig: IEnvironmentConfig,
    ): Retrofit {
        return retrofitBuilder(okHttpClient, environmentConfig)
    }

    @AuthRetrofitClient
    @Provides
    fun provideAuthRetrofitClient(
        @AuthOkHttpClient okHttpClient: OkHttpClient,
        environmentConfig: IEnvironmentConfig,
    ): Retrofit {
        return retrofitBuilder(okHttpClient, environmentConfig)
    }

    @Provides
    fun provideSchedulerProvider(): ISchedulerProvider {
        return SchedulerProvider()
    }

    private fun retrofitBuilder(
        okHttpClient: OkHttpClient,
        environmentConfig: IEnvironmentConfig,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                ).asLenient()
            )
            .baseUrl(environmentConfig.baseURL())
            .build()
    }

    private fun clientBuilder(
        loggerInterceptor: Interceptor,
        authInterceptor: Interceptor? = null,
        requestHeadersInterceptor: Interceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            addInterceptor(loggerInterceptor)
            if (authInterceptor != null)
                addInterceptor(authInterceptor)
            addInterceptor(requestHeadersInterceptor)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
        }
        return client.build()
    }
}
