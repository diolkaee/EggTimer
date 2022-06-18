package com.example.eggtimer.ui.eggtimer.usecases

import com.example.eggtimer.services.egg.EggVariant
import com.example.eggtimer.ui.eggtimer.EggTimerUIState
import com.example.eggtimer.util.StateAction
import com.example.eggtimer.util.UseCase
import com.example.eggtimer.util.UseCaseAction

class SelectEggVariant : UseCase<EggTimerUIState, EggVariant> {
    override suspend fun invoke(
        state: EggTimerUIState,
        parameter: EggVariant
    ): UseCaseAction<EggTimerUIState> {
        return StateAction {
            if (timerRunning) copy(eggVariant = parameter)
            else copy(eggVariant = parameter).resetTimer()
        }
    }
}