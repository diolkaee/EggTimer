package com.example.eggtimer.util

interface UseCaseAction<S> {
    operator fun invoke(state: S): S
}

interface UseCase<S, P> {
    suspend operator fun invoke(state: S, parameter: P): UseCaseAction<S> = StateAction { this }
}
