package datePickerDialog

import UI.main_100
import UI.text_primary
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import customFun.CustomText
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DatePickerDialog(
    onDialogDismiss: () -> Unit,
    onConfirm: (year: Int, month: Int) -> Unit
) {
    // 현재의 년도와 월을 가져옵니다.
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH는 0에서 시작하므로 1을 더해줍니다.

    // 초기 값을 현재의 년도와 월로 설정합니다.
    var yearSelected by remember { mutableStateOf(currentYear) }
    var monthSelected by remember { mutableStateOf(currentMonth) }

    AlertDialog(
        modifier = Modifier.size(400.dp, 300.dp),
        onDismissRequest = {
            onDialogDismiss()
        },
        title = { },
        text = {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
            ) {
                DateTextRow()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    YearsMonthsPicker(
                        onYearSelected = { year ->
                            yearSelected = year
                        },
                        onMonthSelected = { month ->
                            monthSelected = month
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ConfirmButton {
                        onConfirm(yearSelected, monthSelected)
                        onDialogDismiss()
                    }
                }
            }
        },
        shape = RoundedCornerShape(16.dp),
        confirmButton = { }
    )
}

@Composable
fun ConfirmButton(onClick: () -> Unit) {
    Box(modifier = Modifier.size(24.dp)) {
        Icon(
            Icons.Default.Check,
            contentDescription = "Check",
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onClick),
            tint = main_100
        )
    }
}

@Composable
fun DateTextRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        CustomText("언제", main_100, 18.sp, FontWeight.SemiBold, TextAlign.Start)
        CustomText("로 갈까요?", text_primary, 18.sp, FontWeight.SemiBold, TextAlign.Start)
    }
}