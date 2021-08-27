package com.flower.flower.presentation.ui.flowersList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flower.base.domain.model.UIComponent
import com.flower.flower.R
import com.flower.flower.domain.usecase.GetFlowersUseCase
import com.flower.flower.domain.usecase.SearchFlowersUserCase
import com.flower.remote.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowersListViewModel @Inject constructor(
    private val getFlowersUseCase: GetFlowersUseCase,
    private val searchFlowersUserCase: SearchFlowersUserCase
) : ViewModel() {

    val state: MutableState<FlowersListState> = mutableStateOf(FlowersListState())

    init {
        getFlowers()
    }

    fun onTriggerEvent(event: FlowersListEvents) {
        when (event) {
            is FlowersListEvents.SearchFlowers -> {
                searchFlowers(event.searchText)
            }
            is FlowersListEvents.DialogVisibility -> {
                showDialog(visibility = false)
            }
        }
    }

    fun getFlowers() {
        viewModelScope.launch {
            getFlowersUseCase.execute().collect {
                when (it) {
                    is Resource.Success -> {
                        state.value = state.value.copy(isLoading = false, flowers = it.data)
                    }
                    is Resource.Failure -> {
                        state.value = state.value.copy(isLoading = false)
                        showDialog(
                            uiComponent = UIComponent.Dialog(
                                description = R.string.something_went_wrong
                            ),
                            visibility = true
                        )
                    }
                    is Resource.Loading -> {
                        state.value = state.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun searchFlowers(searchText: String) {
        viewModelScope.launch {
            searchFlowersUserCase.execute(searchText).collect {
                when (it) {
                    is Resource.Success -> {
                        state.value = state.value.copy(isLoading = false, flowers = it.data)
                    }
                    is Resource.Failure -> {
                        state.value = state.value.copy(isLoading = false)
                    }
                    is Resource.Loading -> {
                        state.value = state.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    private fun showDialog(uiComponent: UIComponent.Dialog? = null, visibility: Boolean) =
        with(state) {
            if (visibility) value = value.copy(dialog = uiComponent)
            value = value.copy(dialogVisibility = visibility)
        }
}
