package mainCalendarScreen

import UI.gray_100
import UI.gray_40
import UI.suitFamily
import UI.text_primary
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import custom.CustomText
import datePickerDialog.DatePickerDialog
import java.util.*

@Composable
fun AppUI(
    onSettingsClick: () -> Unit,
    onAddScheduleClick: () -> Unit,
) { // 툴바와 달력 레이아웃
    // 현재의 년도와 월을 가져옵니다.
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH는 0에서 시작하므로 1을 더해줍니다.

    var selectedYear by remember { mutableStateOf(currentYear) }
    var selectedMonth by remember { mutableStateOf(currentMonth) }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .padding(start = 8.dp, end = 8.dp)
    ) {
        TopAppBarLayout(
            year = selectedYear, month = selectedMonth,
            onSettingsClick = onSettingsClick,
            onAddScheduleClick = onAddScheduleClick,
            onDateSelected = { y, m ->  // lambda to update selectedYear and selectedMonth
                selectedYear = y
                selectedMonth = m
            }
        ) // 상단 툴바
        Spacer(modifier = Modifier.height(20.dp))

        CustomCalendar(year = selectedYear, month = selectedMonth) // 달력
    }
}

@Composable
fun TopAppBarLayout(
    year: Int, month: Int,
    onSettingsClick: () -> Unit,
    onAddScheduleClick: () -> Unit,
    onDateSelected: (Int, Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        // 왼쪽: 로고 아이콘과 "PLDA" 텍스트
        TopAppBarLeft()

        // 가운데: 년도와 월 이동
        TopAppBarCenter(year, month, onDateSelected)

        // 오른쪽: 검색 창, 일정 추가 버튼, 설정 버튼
        TopAppBarRight(
            onSettingsClick = onSettingsClick,
            onAddScheduleClick = onAddScheduleClick
        )
    }
}

@Composable
fun TopAppBarLeft() {
    Box(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart), // 왼쪽 정렬
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource("image/plda.png"),
                contentDescription = "PLDA Logo",
                modifier = Modifier.size(32.dp)
            )
            CustomText("PLDA", gray_100, 17.sp, FontWeight.ExtraBold)
        }
    }
}

@Composable
fun TopAppBarCenter(
    year: Int, month: Int,
    onDateSelected: (Int, Int) -> Unit
) {
    var showDatePickerDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .clickable(onClick = { showDatePickerDialog = true }),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText("${year}년 ${month}월", text_primary, 19.sp, FontWeight.SemiBold)
            Icon(
                painterResource("image/expand_more.svg"),
                contentDescription = "Drop Down Arrow",
                tint = gray_40,
                modifier = Modifier.size(18.dp).padding(start = 4.dp)
            )
        }
        if (showDatePickerDialog) {
            DatePickerDialog(
                onDialogDismiss = { showDatePickerDialog = false }, // Dialog 닫기 로직 구현
                onConfirm = { selectedYear, selectedMonth ->
                    onDateSelected(selectedYear, selectedMonth)
                    showDatePickerDialog = false // 선택 후 다이얼로그 닫기
                }
            )
        }
    }
}

@Composable
fun TopAppBarRight(
    onSettingsClick: () -> Unit,
    onAddScheduleClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar()

            Box(modifier = Modifier.size(24.dp)) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = { onAddScheduleClick() })
                )
            }

            Box(modifier = Modifier.size(24.dp)) {
                Icon(
                    painterResource("image/setting.svg"),
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = { onSettingsClick() })
                )
            }
        }
    }
}