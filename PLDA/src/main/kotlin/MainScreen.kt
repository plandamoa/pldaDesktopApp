import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

enum class Screen {
    LOGIN, MAIN
}

@Composable
fun AppScreen() {
    var currentScreen by remember { mutableStateOf(Screen.LOGIN) }

    when (currentScreen) {
        Screen.LOGIN -> LoginScreen {
            currentScreen = Screen.MAIN
        }
        Screen.MAIN -> AppUI()
    }
}

@Composable
fun AppUI() {
    // 툴바와 달력 레이아웃
    Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        TopAppBar() // 상단 툴바
        Spacer(modifier = Modifier.height(16.dp))
        CalendarHeader(year = 2023, month = 10) // 년도와 월 표시
        Spacer(modifier = Modifier.height(16.dp))
        CustomCalendar(year = 2023, month = 10) // 달력
    }
}

@Composable
fun TopAppBar() {
    Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // 로고 (예시)
        Text(text = "PLDA", fontWeight = FontWeight.Bold, fontSize = 24.sp)

        // 검색 및 설정 아이콘
        Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(Icons.Default.Search, contentDescription = "Search")
            Icon(Icons.Default.Settings, contentDescription = "Settings")
        }
    }
}

@Composable
fun CalendarHeader(year: Int, month: Int) {
    Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
    ) {
        // 년도와 월 표시 텍스트
        Text(text = "${year}년 ${month}월", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}


@Composable
fun CustomCalendar(year: Int, month: Int) {
    val daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth()
    val firstDayOfWeek = LocalDate.of(year, month, 1).dayOfWeek.value % 7

    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp)
    ) {
        Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("일", "월", "화", "수", "목", "금", "토").forEach { day ->
                Text(day, modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.height(8.dp)) // 요일과 날짜 사이 공백

        var day = 1
        for (i in 0..4) {
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (j in 0..6) {
                    if (i == 0 && j < firstDayOfWeek || day > daysInMonth) {
                        Box(modifier = Modifier.weight(1f).aspectRatio(1.6f)) { } // 빈 날짜
                    } else {
                        Box(
                                modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1.6f)
                                        .border(1.dp, Color.Gray)
                                        .clickable {
                                                   // 날짜 선택 시 작동 코드 작성
                                        },
                                contentAlignment = Alignment.TopStart
                        ) {
                            Text(text = "$day",
                                    modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                            )
                            day++
                        }
                    }
                }
            }
        }
    }
}