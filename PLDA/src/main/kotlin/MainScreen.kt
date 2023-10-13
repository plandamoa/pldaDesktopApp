import UI.*
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

@Composable
fun AppUI() { // 툴바와 달력 레이아웃
    val year = 2023 // 현재 년도
    val month = 12 //현재 월
    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp)
                    .padding(start = 8.dp, end = 8.dp)
    ) {
        TopAppBarLayout(year = year, month = month) // 상단 툴바

        Spacer(modifier = Modifier.height(16.dp))

        CustomCalendar(year = year, month = month) // 달력
    }
}

@Composable
fun TopAppBarLayout(year: Int, month: Int) {
    Box(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
            contentAlignment = Alignment.Center
    ) {
        // 왼쪽: 로고 아이콘과 "PLDA" 텍스트
        TopAppBarLeft()

        // 가운데: 년도와 월 이동
        TopAppBarCenter(year, month)


        // 오른쪽: 검색 창, 일정 추가 버튼, 설정 버튼
        TopAppBarRight()
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
                    modifier = Modifier.size(24.dp)
            )
            Text(
                text = "PLDA",
                fontFamily = suitFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = gray_100
            )
        }
    }
}

@Composable
fun TopAppBarCenter(year: Int, month: Int) {
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
            Text(
                    text = "${year}년 ${month}월",
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = text_primary
            )
            Icon(
                    painterResource("image/expand_more.svg"),
                    contentDescription = "Drop Down Arrow",
                    tint = gray_40,
                    modifier = Modifier
                            .size(16.dp)
                            .padding(start = 4.dp)
            )
        }
        if (showDatePickerDialog) {
            DatePickerDialog() { showDatePickerDialog = false }
        }
    }
}

@Composable
fun TopAppBarRight() {
    val showAddScheduleDialog = remember { mutableStateOf(false) }

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
                                .clickable(onClick = { showAddScheduleDialog.value = true })
                )
            }

            Box(modifier = Modifier.size(24.dp)) {
                Icon(
                        painterResource("image/setting.svg"),
                        contentDescription = "Settings",
                        modifier = Modifier
                                .size(24.dp)
                                .clickable(onClick = {  })
                )
            }
        }
    }
    if (showAddScheduleDialog.value) {
        AddScheduleDialog(
            isDialogVisible = showAddScheduleDialog,
            onDismiss = { showAddScheduleDialog.value = false }
        )
    }
}