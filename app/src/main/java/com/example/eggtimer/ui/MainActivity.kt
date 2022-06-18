package com.example.eggtimer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.eggtimer.ui.eggtimer.EggTimerScreen
import com.example.eggtimer.ui.theme.EggTimerTheme
import com.example.eggtimer.ui.eggtimer.EggTimerUIState
import com.example.eggtimer.ui.eggtimer.EggTimerViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EggTimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val eggTimerViewModel = getViewModel<EggTimerViewModel>()
                    val eggTimerUIState by eggTimerViewModel.uiState.collectAsState()

                    EggTimerScreen(
                        uiState = eggTimerUIState,
                        onEggVariantChanged = { eggTimerViewModel.selectEggVariant(it) },
                        onToggleTimer = { eggTimerViewModel.toggleTimer() },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EggTimerTheme {
        EggTimerScreen(EggTimerUIState(), {}, {})
    }
}