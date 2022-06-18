package com.example.eggtimer.util

interface UiState

typealias StateChangeBlock<S> = S.() -> S

@JvmInline
value class StateAction<S>(private val block: StateChangeBlock<S>) : UseCaseAction<S> {
    override operator fun invoke(state: S) = state.block()
}
