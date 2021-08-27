package com.flower.base.domain.model

import com.flower.base.R

sealed class UIComponent {

    data class Dialog(
        val title: Int = R.string.empty,
        val description: Int = R.string.empty,
        val positiveButtonText: Int = R.string.ok
    ) : UIComponent()

    data class None(
        val message: Int,
    ) : UIComponent()
}
