package com.flower.flower.data.mapper

import com.flower.base.utils.BaseMapper
import com.flower.flower.data.entity.FlowersListResponse
import com.flower.flower.domain.model.FlowersListModel

class FlowersListEntityToModelMapper : BaseMapper<FlowersListResponse, FlowersListModel>() {

    override fun map(value: FlowersListResponse): FlowersListModel {
        return FlowersListModel(
            flowers = value.flowers,
            meta = value.meta
        )
    }
}
