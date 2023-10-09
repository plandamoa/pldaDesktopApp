package numberPicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MainActivityUI() {

    val scrollState = rememberScrollState()

    Column(
            modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
    ) {
        Column(
                Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
        ) {
            HoursNumberPicker3()
        }
    }
}

@Composable
private fun HoursNumberPicker3() {
    var state by remember { mutableStateOf<Hours>(FullHours(9, 20)) }

    HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp), leadingZero = true,

        value = state,
        onValueChange = {
            state = it
        },
        minutesRange = IntProgression.fromClosedRange(0, 50, 10),
        hoursDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "h"
            )
        },
        minutesDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "m"
            )
        }
    )
}

