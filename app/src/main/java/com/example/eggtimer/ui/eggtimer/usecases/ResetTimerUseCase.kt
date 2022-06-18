package com.example.eggtimer.ui.eggtimer.usecases

import com.example.eggtimer.ui.eggtimer.EggTimerUIState
import com.example.eggtimer.util.StateAction
import com.example.eggtimer.util.UseCase
import com.example.eggtimer.util.UseCaseAction

class ResetTimerUseCase : UseCase<EggTimerUIState, Unit> {
    override suspend fun invoke(
        state: EggTimerUIState,
        parameter: Unit
    ): UseCaseAction<EggTimerUIState> {
        return StateAction {
            resetTimer()
        }
    }
}