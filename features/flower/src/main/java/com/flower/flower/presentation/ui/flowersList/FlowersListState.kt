package com.flower.flower.presentation.ui.flowersList

import com.flower.base.domain.model.UIComponent
import com.flower.flower.domain.model.FlowersListModel

data class FlowersListState(
    val flowers: FlowersListModel? = null,
    val isLoading: Boolean = false,
    var dialogVisibility: Boolean = false,
    val dialog: UIComponent.Dialog? = null
)
