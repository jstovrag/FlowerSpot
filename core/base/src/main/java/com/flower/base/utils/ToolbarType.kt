package com.flower.base.utils

import androidx.navigation.NavHostController

data class ToolbarType(
    val theme: ToolbarTheme = ToolbarTheme.None,
    val titleTxt: String? = null,
    val leftIcon: Int? = null,
    val navHostController: NavHostController? = null
)

sealed class ToolbarTheme {
    object White : ToolbarTheme()
    object Logo : ToolbarTheme()
    object None : ToolbarTheme()
}
