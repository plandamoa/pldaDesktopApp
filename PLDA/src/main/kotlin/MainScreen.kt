import UI.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        TopAppBar(year = year, month = month) // 상단 툴바
        Spacer(modifier = Modifier.height(16.dp))
        CustomCalendar(year = year, month = month) // 달력
    }
}

@Composable
fun TopAppBar(year: Int, month: Int) {
    Box(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
    ) {
        // 왼쪽: 로고 아이콘과 "PLDA" 텍스트
        Row(
                modifier = Modifier.align(Alignment.CenterStart), // 왼쪽 정렬
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                    painter = painterResource("image/plda.png"),
                    contentDescription = "PLDA Logo",
                    modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(1.dp))
            Text(
                    text = "PLDA",
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp
            )
        }

        // 가운데: 년도와 월 이동
        Row(
                modifier = Modifier
                        .align(Alignment.Center) // 가운데 정렬
                        .clickable {
                            /* 클릭 시 수행할 동작 */
                        },
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                    text = "${year}년 ${month}월",
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 28.sp,
            )
            Icon(
                    painterResource("image/expand_more.svg"),
                    contentDescription = "Drop Down Arrow",
                    tint = arrowButtonGray,
                    modifier = Modifier
                            .size(30.dp)
                            .padding(start = 12.dp)
            )
        }

        // 오른쪽: 검색 창, 일정 추가 버튼, 설정 버튼
        Row(
                modifier = Modifier.align(Alignment.CenterEnd), // 오른쪽 정렬
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
        ) {
            // 검색 창
            SearchBar()

            // 일정 추가 버튼
            Icon(
                    Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier
                            .size(38.dp)
                            .clickable(
                                onClick = {
                                    // 여기에 클릭시 수행될 코드를 추가합니다.
                                }
                    )
            )

            // 설정 버튼
            Icon(
                    painterResource("image/setting.svg"),
                    contentDescription = "Settings",
                    modifier = Modifier
                            .size(38.dp)
                            .clickable(
                                onClick = {
                                    // 여기에 클릭시 수행될 코드를 추가합니다.
                                }
                    )
            )
        }
    }
}

@Composable
fun CustomCalendar(year: Int, month: Int) {
    val daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth()
    val firstDayOfWeek = LocalDate.of(year, month, 1).dayOfWeek.value % 7
    val previousMonthDays = if(month == 1) {
        LocalDate.of(year - 1, 12, 1).lengthOfMonth()
    } else {
        LocalDate.of(year, month - 1, 1).lengthOfMonth()
    }
    var previousMonthDayToShow = previousMonthDays - firstDayOfWeek + 1
    var nextMonthDay = 1
    var day = 1

    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
    ) {
        Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("일", "월", "화", "수", "목", "금", "토").forEach { day ->
                Text(
                        day,
                        modifier = Modifier.weight(1f),
                        fontFamily = suitFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = if (day == "일") sundayRed
                            else dayOfTheWeekGray,
                        textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        val DAYS_VERTICAL_SIZE = 1.8f // 달력 날짜 칸 세로 비율

        for (i in 0..5) {
            Divider(color = dateLightGray, thickness = 1.4.dp)

            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (j in 0..6) {
                    Box( // 날짜 영역 박스
                            modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1.8f)
                                    .clickable {
                                        /* 클릭 시 동작 */
                                    },
                            contentAlignment = Alignment.TopEnd
                    ) {
                        when {
                            i == 0 && j < firstDayOfWeek -> { // 이전 달의 날짜
                                Text(
                                        text = "$previousMonthDayToShow",
                                        modifier = Modifier.padding(8.dp),
                                        fontFamily = suitFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp,
                                        color = dateLightGray
                                )
                                previousMonthDayToShow++
                            }

                            day <= daysInMonth -> { // 현재 달의 날짜
                                Text(
                                        text = "$day",
                                        modifier = Modifier.padding(8.dp),
                                        fontFamily = suitFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp,
                                        color = dateBlack
                                )
                                day++
                            }

                            else -> { // 다음 달의 날짜
                                Text(
                                        text = "$nextMonthDay",
                                        modifier = Modifier.padding(8.dp),
                                        fontFamily = suitFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp,
                                        color = dateLightGray
                                )
                                nextMonthDay++
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    Box(
            modifier = Modifier
                    .height(50.dp)
                    .width(140.dp)
                    .background(color = searchBackGray, shape = RoundedCornerShape(16.dp))
    ) {
        Icon(
                painterResource("image/search.svg"),
                contentDescription = "Search Icon",
                tint = searchGray,
                modifier = Modifier
                        .padding(start = 18.dp, top = 11.5.dp)
                        .size(23.dp)
        )
        BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = TextStyle(color = searchGray, fontSize = 18.sp),
                cursorBrush = SolidColor(searchGray),
                singleLine = true,
                modifier = Modifier
                        .padding(start = 52.dp, end = 8.dp) // Icon의 width와 padding을 고려
                        .fillMaxHeight()
                        .padding(vertical = 12.5.dp) // 수직 패딩을 조절하여 텍스트가 세로 중앙에 위치하도록 함
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused }
        )

        if (!isFocused && text.isEmpty()) { // 기본 텍스트
            Text(
                    text = "일정검색",
                    color = searchGray,
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    modifier = Modifier
                            .padding(start = 52.dp, end = 8.dp)
                            .padding(vertical = 12.5.dp)
            )
        }
    }
}