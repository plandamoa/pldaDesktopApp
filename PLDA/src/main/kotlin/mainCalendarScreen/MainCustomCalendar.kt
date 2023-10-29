package mainCalendarScreen

import UI.*
import addScheduleScreen.RenderEvents
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import customFun.CustomText

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

    val DAYS_VERTICAL_SIZE = 1.75f // 달력 날짜 칸 세로 비율

    Column(
        modifier = Modifier.fillMaxSize()
            .background(bg_white)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("일", "월", "화", "수", "목", "금", "토").forEach { day ->
                Box(
                    Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CustomText(
                        day, if (day == "일") sundayRed else dayOfTheWeekGray,
                        14.sp, FontWeight.SemiBold, TextAlign.Center
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

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
                                Box(Modifier.padding(6.dp)) {
                                    CustomText("$previousMonthDayToShow", text_lowEmphasis, 13.sp, FontWeight.SemiBold, TextAlign.End)
                                }
                                previousMonthDayToShow++
                            }

                            day <= daysInMonth -> { // 현재 달의 날짜
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Box(Modifier.padding(6.dp)) {
                                        CustomText("$day", text_primary, 13.sp, FontWeight.SemiBold, TextAlign.End)
                                    }
                                    RenderEvents(year, month, day)
                                }
                                day++
                            }

                            else -> { // 다음 달의 날짜
                                Box(Modifier.padding(6.dp)) {
                                    CustomText("$nextMonthDay", text_lowEmphasis, 13.sp, FontWeight.SemiBold, TextAlign.End)
                                }
                                nextMonthDay++
                            }
                        }
                    }
                }
            }
        }
    }
}