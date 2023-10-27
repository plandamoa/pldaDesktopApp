package mainCalendarScreen

import UI.*
import addScheduleScreen.events
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomCalendar(year: Int, month: Int) {
    val daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth()
    val firstDayOfWeek = LocalDate.of(year, month, 1).dayOfWeek.value % 7
    val previousMonthDays = if (month == 1) {
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
            .background(bg_white)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("일", "월", "화", "수", "목", "금", "토").forEach { day ->
                Text(
                    day, modifier = Modifier.weight(1f),
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = if (day == "일") sundayRed
                                else dayOfTheWeekGray,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        val DAYS_VERTICAL_SIZE = 1.75f // 달력 날짜 칸 세로 비율

        for (i in 0..5) { // todo: 화면 크기 조정 시 달력 열 방향 높이 유동적으로 변화하도록 수정
            Divider(color = gray_20, thickness = 1.2.dp)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (j in 0..6) {
                    Box( // 날짜 영역 박스
                        modifier = Modifier
                            .padding(1.dp).weight(1f)
                            .aspectRatio(DAYS_VERTICAL_SIZE)
                            .combinedClickable(
                                onDoubleClick = { println("clicked") }
                                //todo
                            ) { },
                        contentAlignment = Alignment.TopEnd
                    ) {
                        when {
                            i == 0 && j < firstDayOfWeek -> { // 이전 달의 날짜
                                CalendarDayText("$previousMonthDayToShow", text_lowEmphasis)
                                previousMonthDayToShow++
                            }

                            day <= daysInMonth -> { // 현재 달의 날짜
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.End
                                ) {
                                    CalendarDayText("$day", text_primary)
                                    events[day]?.forEachIndexed { index, eventName ->
                                        // Limit the number of events to 3 for this example
                                        if (index < 3) {
                                            EventBox(eventName, Color.Gray, year, month, day)
                                            Spacer(Modifier.padding(1.dp))
                                        }
                                    }
                                }
                                day++
                            }

                            else -> { // 다음 달의 날짜
                                CalendarDayText("$nextMonthDay", text_lowEmphasis)
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
fun CalendarDayText(text: String, color: Color) {
    Text(
        modifier = Modifier.padding(6.dp),
        text = text,
        fontFamily = suitFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        color = color,
        textAlign = TextAlign.End
    )
}