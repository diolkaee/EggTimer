package com.example.eggtimer.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class StatefulViewModel<S : UiState>(state: S) : ViewModel() {
    private val _uiState: MutableStateFlow<S> = MutableStateFlow(state)
    val uiState: StateFlow<S> = _uiState

    fun <P> runUseCase(
        useCase: UseCase<S, P>,
        parameter: P,
        onCompleteBlock: ((newState: S) -> Unit) = {},
    ) {
        viewModelScope.launch {
            _uiState.update { useCase(it, parameter)(it) }
            onCompleteBlock(_uiState.value)
        }
    }

    fun runUseCase(
        useCase: UseCase<S, Unit>,
        onCompleteBlock: ((newState: S) -> Unit) = {},
    ) = runUseCase(useCase, Unit, onCompleteBlock)
}