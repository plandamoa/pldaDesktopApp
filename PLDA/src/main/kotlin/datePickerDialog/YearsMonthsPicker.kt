package datePickerDialog

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun YearsMonthsPicker(
    onYearSelected: (Int) -> Unit, // 추가: 선택된 년도를 받을 콜백
    onMonthSelected: (Int) -> Unit // 추가: 선택된 월을 받을 콜백
) {
    var state by remember { mutableStateOf<Date>(FullDate(2023, 10)) }

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

