package com.example.eggtimer.ui.eggtimer.usecases

import com.example.eggtimer.ui.eggtimer.EggTimerUIState
import com.example.eggtimer.util.StateAction
import com.example.eggtimer.util.UseCase
import com.example.eggtimer.util.UseCaseAction

class UpdateTimerUseCase : UseCase<EggTimerUIState, (Int) -> Int> {
    override suspend fun invoke(
        state: EggTimerUIState,
        parameter: (Int) -> Int
    ): UseCaseAction<EggTimerUIState> {
        return StateAction {
            copy(timer = parameter(this.timer))
        }
    }
}