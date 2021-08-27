package com.flower.flower.presentation.ui.flowersList

sealed class FlowersListEvents {

    data class SearchFlowers(
        val searchText: String
    ) : FlowersListEvents()

    data class DialogVisibility(
        val visibility: Boolean
    ) : FlowersListEvents()
}
