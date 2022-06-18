package com.example.eggtimer.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eggtimer.ui.theme.EggTimerTheme

@Composable
fun EggTextBox(
    text: String,
    modifier: Modifier = Modifier
) {
    AutoSizeText(
        text = text,
        textAlign = TextAlign.Center,
        style = TextStyle(
            color = MaterialTheme.colors.secondary,
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier
            .border(
                width = 4.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = RoundedCornerShape(20)
            )
            .padding(10.dp)
    )
}

@Preview
@Composable
fun EggTextBoxPreview() {
    EggTimerTheme {
        EggTextBox(
            text = "example text",
            modifier = Modifier.size(100.dp)
        )
    }
}

//TextView that scales its TextSize to its available size
//Taken from: https://stackoverflow.com/questions/63971569/androidautosizetexttype-in-jetpack-compose
@Composable
fun AutoSizeText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    textAlign: TextAlign = TextAlign.Start
) {
    // Set fontSize to a huge value for first drawing so we can downscale
    var scaledTextSize by remember { mutableStateOf(style.copy(fontSize = 100.sp)) }
    var readyToDraw by remember(text) { mutableStateOf(false) }

    Text(
        text = text,
        modifier = modifier
            .drawWithContent { if (readyToDraw) drawContent() },
        textAlign = textAlign,
        style = scaledTextSize,
        softWrap = false,
        onTextLayout = {
            when {
                it.didOverflowWidth -> {
                    scaledTextSize =
                        scaledTextSize.copy(fontSize = scaledTextSize.fontSize * (it.size.width / it.multiParagraph.width))
                }
                it.didOverflowHeight -> {
                    scaledTextSize =
                        scaledTextSize.copy(fontSize = scaledTextSize.fontSize * (it.size.height / it.multiParagraph.height))
                }
                else -> {
                    readyToDraw = true
                }
            }
        })
}
