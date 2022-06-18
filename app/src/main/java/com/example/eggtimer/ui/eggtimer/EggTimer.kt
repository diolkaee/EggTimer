package com.example.eggtimer.ui.eggtimer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eggtimer.R
import com.example.eggtimer.services.egg.EggVariant
import com.example.eggtimer.ui.components.EggTextBox
import com.example.eggtimer.ui.components.StepSlider
import com.example.eggtimer.ui.theme.EggTimerTheme
import java.util.concurrent.TimeUnit

private fun Int.toTimeString() = String.format(
    "%02d:%02d",
    TimeUnit.SECONDS.toMinutes(this.toLong()),
    TimeUnit.SECONDS.toSeconds(this.toLong() % TimeUnit.MINUTES.toSeconds(1))
)

@Composable
fun EggTimerScreen(
    uiState: EggTimerUIState,
    onEggVariantChanged: (EggVariant) -> Unit,
    onToggleTimer: () -> Unit,
    onClearTimer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(40.dp)
            .fillMaxSize(),
    ) {
        EggTextBox(
            text = "${uiState.pressure} hPa",
            modifier = Modifier
                .padding(40.dp)
                .fillMaxWidth()
        )
        StepSlider(
            steps = EggVariant.values().size,
            value = uiState.eggVariant.ordinal,
            onValueChange = { onEggVariantChanged(EggVariant.values()[it]) }
        )
        Spacer(modifier = Modifier.height(40.dp))
        EggTextBox(
            uiState.timer.toTimeString(),
            modifier = Modifier
                .padding(40.dp)
                .fillMaxWidth()
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        EggButton(onClick = onToggleTimer, contentDescription = "Start timer")
    }
}

@Composable
fun EggButton(
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Button(
        shape = CircleShape,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant),
        modifier = modifier
    ) {
        Image(painterResource(id = R.drawable.ic_egg), contentDescription = contentDescription)
    }
}

@Composable
@Preview
fun PreviewEggTimer() {
    EggTimerTheme {
        EggTimerScreen(EggTimerUIState(), {}, {}, {})
    }
}
