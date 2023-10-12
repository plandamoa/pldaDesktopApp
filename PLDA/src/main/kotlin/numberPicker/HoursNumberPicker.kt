package numberPicker

import UI.main_100
import UI.main_80
import UI.sub_red_primary
import UI.suitFamily
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlin.math.abs

sealed interface Hours {
    val years: Int
    val months: Int
}

data class FullHours(
    override val years: Int,
    override val months: Int,
) : Hours // todo: Hours와 FullHours 두 개의 클래스를 하나로 통합하기

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
        dividersColor: Color = main_80, // todo: divider가 아닌 모서리가 둥근 사각형으로 바꾸기
        textStyle: TextStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = suitFamily,
            fontWeight = FontWeight.SemiBold,
            color = main_100
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
        dividersColor: Color = sub_red_primary,
        textStyle: TextStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = suitFamily,
            fontWeight = FontWeight.SemiBold,
            color = main_100
        )
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NumberPicker(
            label = {
                "${if (leadingZero && abs(it) < 10) "0" else ""}$it"
            },
            value = value.years,
            onValueChange = {
                onValueChange(value.copy(years = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            range = hoursRange
        )

        hoursDivider?.invoke()

        NumberPicker(
            label = {
                "${if (leadingZero && abs(it) < 10) "0" else ""}$it"
            },
            value = value.months,
            onValueChange = {
                onValueChange(value.copy(months = it))
            },
            dividersColor = dividersColor,
            textStyle = textStyle,
            range = minutesRange
        )

        minutesDivider?.invoke()
    }
}