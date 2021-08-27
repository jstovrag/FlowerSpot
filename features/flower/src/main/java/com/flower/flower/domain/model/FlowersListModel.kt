package com.flower.flower.domain.model

import com.flower.flower.data.entity.Flower
import com.flower.flower.data.entity.Meta

data class FlowersListModel(
    val flowers: List<Flower>,
    val meta: Meta,
)
