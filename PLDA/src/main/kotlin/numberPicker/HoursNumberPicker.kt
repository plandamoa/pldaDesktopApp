package numberPicker

import UI.suitFamily
import androidx.compose.foundation.layout.Row
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlin.math.abs

sealed interface Hours {
    val hours: Int
    val minutes: Int
}

data class FullHours(
    override val hours: Int,
    override val minutes: Int,
) : Hours

@Composable
fun HoursNumberPicker(
        modifier: Modifier = Modifier,
        value: Hours,
        leadingZero: Boolean = true,
        yearsRange: Iterable<Int> = (2000..2050),
        monthsRange: Iterable<Int> = (1..12),
        yearsDivider: (@Composable () -> Unit)? = null,
        monthsDivider: (@Composable () -> Unit)? = null,
        onValueChange: (Hours) -> Unit,
        dividersColor: Color = MaterialTheme.colors.primary,
        textStyle: TextStyle = TextStyle(
            fontSize = 20.sp,
            fontFamily = suitFamily,
            fontWeight = FontWeight.SemiBold,
        )
) {
    when (value) {
        is FullHours ->
            FullHoursNumberPicker(
                    modifier = modifier,
                    value = value,
                    leadingZero = leadingZero,
                    hoursRange = yearsRange,
                    minutesRange = monthsRange,
                    hoursDivider = yearsDivider,
                    minutesDivider = monthsDivider,
                    onValueChange = onValueChange,
                    dividersColor = dividersColor,
                    textStyle = textStyle,
            )
    }
}

@Composable
fun FullHoursNumberPicker(
        modifier: Modifier = Modifier,
        value: FullHours,
        leadingZero: Boolean = true,
        hoursRange: Iterable<Int>,
        minutesRange: Iterable<Int>,
        hoursDivider: (@Composable () -> Unit)? = null,
        minutesDivider: (@Composable () -> Unit)? = null,
        onValueChange: (Hours) -> Unit,
        dividersColor: Color = MaterialTheme.colors.primary,
        textStyle: TextStyle = TextStyle(
            fontSize = 20.sp,
            fontFamily = suitFamily,
            fontWeight = FontWeight.SemiBold,
        )
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NumberPicker(
            modifier = Modifier.weight(1f),
            label = {
                "${if (leadingZero && abs(it) < 10) "0" else ""}$it"
            },
            value = value.hours,
            onValueChange = {
                onValueChange(value.copy(hours = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            range = hoursRange
        )

        hoursDivider?.invoke()

        NumberPicker(
            modifier = Modifier.weight(1f),
            label = {
                "${if (leadingZero && abs(it) < 10) "0" else ""}$it"
            },
            value = value.minutes,
            onValueChange = {
                onValueChange(value.copy(minutes = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            range = minutesRange
        )

        minutesDivider?.invoke()
    }
}