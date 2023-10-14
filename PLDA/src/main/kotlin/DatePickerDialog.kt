import UI.main_100
import UI.suitFamily
import UI.text_primary
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import numberPicker.YearsMonthsPicker

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DatePickerDialog(onDialogDismiss: () -> Unit) {
    AlertDialog(
            modifier = Modifier.size(400.dp, 300.dp),
            onDismissRequest = onDialogDismiss,
            title = { },
            text = {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 16.dp)
                ) {
                    TextRow()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        YearsMonthsPicker()
                        Spacer(modifier = Modifier.weight(1f)) // 이 Spacer를 추가하여 남은 공간을 차지하게 함
                        ConfirmButton(
                            onClick = {
                                /*여기에 선택된 날짜를 전달*/
                            }
                        )
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
                .clickable(onClick = onClick), // todo: Confirm 버튼 누르면 창 닫기
            tint = main_100
        )
    }
}

@Composable
fun TextRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "언제",
            fontFamily = suitFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = main_100
        )
        Text(
            text = "로 갈까요?",
            fontFamily = suitFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = text_primary
        )
    }
}
