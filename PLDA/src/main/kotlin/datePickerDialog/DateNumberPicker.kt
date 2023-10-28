package datePickerDialog

import UI.text_primary
import UI.text_third
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import custom.CustomText

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
        Spacer(Modifier.width(8.dp))
        CustomText("년", text_primary)
        Spacer(Modifier.width(8.dp))
        NumberPicker(
            value = value.month,
            range = monthsRange,
            onValueChange = { month ->
                onValueChange(FullDate(value.year, month))
            }
        )
        Spacer(Modifier.width(8.dp))
        CustomText("월", text_primary)
        CustomText("로 갈게요", text_third, 14.sp, FontWeight.Medium)
    }
}