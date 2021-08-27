package com.flower.flower.domain.usecase

import com.flower.flower.domain.model.FlowersListModel
import com.flower.flower.domain.repo.IFlowersRepo
import com.flower.remote.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFlowersUseCase @Inject constructor(
    private val flowersRepo: IFlowersRepo,
) {

    suspend fun execute(): Flow<Resource<FlowersListModel>> {
        return flowersRepo.getFlowers()
    }
}
