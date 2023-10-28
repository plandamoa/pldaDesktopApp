package datePickerDialog

import UI.suitFamily
import UI.text_primary
import UI.text_third
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed interface Date {
    val year: Int
    val month: Int
}

data class FullDate(
    override val year: Int,
    override val month: Int
) : Date

@Composable
fun DateNumberPicker(
    modifier: Modifier = Modifier,
    value: Date,
    onValueChange: (Date) -> Unit
) {
    val yearsRange = 2000..2050
    val monthsRange = 1..12

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NumberPicker(
            value = value.year,
            range = yearsRange,
            onValueChange = { year ->
                onValueChange(FullDate(year, value.month))
            }
        )
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
        Spacer(Modifier.width(8.dp))
        NumberPicker(
            value = value.month,
            range = monthsRange,
            onValueChange = { month ->
                onValueChange(FullDate(value.year, month))
            }
        )
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