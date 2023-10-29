package datePickerDialog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun YearsMonthsPicker(
    onYearSelected: (Int) -> Unit, // 추가: 선택된 년도를 받을 콜백
    onMonthSelected: (Int) -> Unit // 추가: 선택된 월을 받을 콜백
) {
    // 현재의 년도와 월을 가져옵니다.
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH는 0에서 시작하므로 1을 더해줍니다.

    var state by remember { mutableStateOf<Date>(FullDate(currentYear, currentMonth)) }

    DateNumberPicker(
        modifier = Modifier.padding(vertical = 16.dp),
        value = state,
        onValueChange = { date ->
            state = date
            onYearSelected(date.year)
            onMonthSelected(date.month)
        }
    )
}

