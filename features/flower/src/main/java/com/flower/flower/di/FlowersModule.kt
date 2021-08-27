package com.flower.flower.di

import com.flower.flower.data.mapper.FlowersListEntityToModelMapper
import com.flower.flower.data.remote.FlowersApi
import com.flower.flower.data.remote.FlowersRemoteSource
import com.flower.flower.data.remote.IFlowersRemoteSource
import com.flower.flower.data.repo.FlowersRepo
import com.flower.flower.domain.repo.IFlowersRepo
import com.flower.flower.domain.usecase.GetFlowersUseCase
import com.flower.remote.di.AuthRetrofitClient
import com.flower.remote.scheduler.ISchedulerProvider
import com.flower.remote.utils.ErrorCodesMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class FlowersModule {

    @Provides
    fun provideFlowersApi(@AuthRetrofitClient retrofit: Retrofit): FlowersApi {
        return retrofit.create(FlowersApi::class.java)
    }

    @Provides
    fun provideFlowersRemoteSource(flowersApi: FlowersApi): IFlowersRemoteSource {
        return FlowersRemoteSource(flowersApi)
    }

    @Provides
    fun provideFlowersListEntityToModelMapper(): FlowersListEntityToModelMapper {
        return FlowersListEntityToModelMapper()
    }

    @Provides
    fun provideFlowersRepo(
        flowersRemoteSource: IFlowersRemoteSource,
        schedulerProvider: ISchedulerProvider,
        errorCodesMapper: ErrorCodesMapper,
        flowersListMapper: FlowersListEntityToModelMapper
    ): IFlowersRepo {
        return FlowersRepo(
            flowersRemoteSource,
            schedulerProvider,
            errorCodesMapper,
            flowersListMapper
        )
    }

    @Provides
    fun provideGetFlowersUseCase(
        flowersRepo: IFlowersRepo
    ): GetFlowersUseCase {
        return GetFlowersUseCase(flowersRepo)
    }
}
