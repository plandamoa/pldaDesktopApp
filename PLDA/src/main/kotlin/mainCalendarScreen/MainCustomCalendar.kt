package mainCalendarScreen

import UI.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

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
                    day,
                    modifier = Modifier.weight(1f),
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = if (day == "일") sundayRed
                    else dayOfTheWeekGray,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        val DAYS_VERTICAL_SIZE = 1.8f // 달력 날짜 칸 세로 비율

        for (i in 0..5) { // todo: 화면 크기 조정 시 달력 열 방향 높이 유동적으로 변화하도록 수정
            Divider(color = gray_20, thickness = 1.2.dp)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (j in 0..6) {
                    Box( // 날짜 영역 박스
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(DAYS_VERTICAL_SIZE)
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
                                    fontSize = 11.sp,
                                    color = text_lowEmphasis
                                )
                                previousMonthDayToShow++
                            }

                            day <= daysInMonth -> { // 현재 달의 날짜
                                Text(
                                    text = "$day",
                                    modifier = Modifier.padding(8.dp),
                                    fontFamily = suitFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 11.sp,
                                    color = text_primary
                                )
                                day++
                            }

                            else -> { // 다음 달의 날짜
                                Text(
                                    text = "$nextMonthDay",
                                    modifier = Modifier.padding(8.dp),
                                    fontFamily = suitFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 11.sp,
                                    color = text_lowEmphasis
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