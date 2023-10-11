import UI.searchGray
import UI.suitFamily
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import numberPicker.YearsMonthsPicker

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun viewCalendarList(onDialogDismiss: () -> Unit) {
    AlertDialog(
            modifier = Modifier.size(600.dp, 300.dp),
            onDismissRequest = onDialogDismiss,
            title = { },
            text = {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text()
                    YearsMonthsPicker()
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onDialogDismiss,
                        contentPadding = PaddingValues(0.dp), // 아이콘 주변의 패딩을 제거
                        modifier = Modifier.size(35.dp), // 버튼의 크기를 정사각형으로 지정
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent, // 배경을 투명하게 설정
                            contentColor = searchGray // 아이콘 색상을 searchGray로 설정
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp,
                            disabledElevation = 0.dp,
                            hoveredElevation = 0.dp
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Confirm",
                            modifier = Modifier.size(30.dp) // 아이콘의 크기를 최대화
                        )
                    }
                }
            },
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
private fun Text() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "언제로 갈까요?",
            fontFamily = suitFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
        )
    }
}
