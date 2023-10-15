package datePickerDialog

import UI.suitFamily
import UI.text_primary
import UI.text_third
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun YearsMonthsPicker() {
    var state by remember { mutableStateOf<Hours>(FullHours(9, 20)) }

    HoursNumberPicker(
        modifier = Modifier
            .padding(vertical = 16.dp), leadingZero = true,

        value = state,
        onValueChange = {
            state = it
        },
        monthsRange = IntProgression.fromClosedRange(1, 12, 1),
        yearsDivider = {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(end = 8.dp),
                textAlign = TextAlign.Center,
                text = "년",
                fontFamily = suitFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = text_primary
            )
        },
        monthsDivider = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    textAlign = TextAlign.Center,
                    text = "월",
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = text_primary
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = "로 갈게요",
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = text_third
                )
            }
        }
    )
}

