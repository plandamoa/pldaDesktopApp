package numberPicker

import androidx.compose.foundation.layout.Box
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
fun ScrolledNumberPickerUI() {
    YearsMonthsPicker()
}

@Composable
private fun YearsMonthsPicker() {
    var state by remember { mutableStateOf<Hours>(FullHours(9, 20)) }

    HoursNumberPicker(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp), leadingZero = true,

        value = state,
        onValueChange = {
            state = it
        },
        monthsRange = IntProgression.fromClosedRange(1, 12, 1),
        yearsDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "년"
            )
        },
        monthsDivider = {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = "월"
            )
        }
    )
}

