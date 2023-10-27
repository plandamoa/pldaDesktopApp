package viewListEventsScreen

import UI.*
import addScheduleScreen.events
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek
import java.time.LocalDate


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewEventListDialog(
    year: Int, month: Int, day: Int,
    onDialogDismiss: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.size(500.dp, 500.dp),
        onDismissRequest = onDialogDismiss,
        title = { TopText(year, month, day) },
        text = {
            Column {
                Text(
                    text = "",
                    fontSize = 1.sp
                )
                // 해당 날짜의 이벤트 리스트를 가져와 각 이벤트에 대해 EventViewBox()를 호출
                events[day]?.forEach { event ->
                    EventViewBox(eventName = event)
                }
            }
        },
        shape = RoundedCornerShape(16.dp),
        confirmButton = { },
        backgroundColor = bg_gray
    )
}

@Composable
fun EventViewBox(eventName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bg_white, shape = RoundedCornerShape(8.dp))
            .padding(24.dp)
    ) {
        Column {
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(
                    text = "from 구글캘린더",
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = text_third
                )
            }
            Text(
                text = eventName,
                fontFamily = suitFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = text_primary
            )
            Text(
                text = "10:00",
                fontFamily = suitFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                color = text_secondary
            )
        }
    }
    Spacer(Modifier.padding(4.dp))
}

@Composable
fun TopText(year: Int, month: Int, day: Int) {
    val date = LocalDate.of(year, month, day)
    val dayOfWeekInKorean = when(date.dayOfWeek) {
        DayOfWeek.MONDAY -> "월요일"
        DayOfWeek.TUESDAY -> "화요일"
        DayOfWeek.WEDNESDAY -> "수요일"
        DayOfWeek.THURSDAY -> "목요일"
        DayOfWeek.FRIDAY -> "금요일"
        DayOfWeek.SATURDAY -> "토요일"
        DayOfWeek.SUNDAY -> "일요일"
    }

    // 오늘 날짜와 선택한 날짜가 동일한 경우 "오늘" 텍스트를 추가합니다.
    val isToday = date == LocalDate.now()
    val displayText = if(isToday) {
        "${month}월 ${day}일 $dayOfWeekInKorean (오늘)"
    } else {
        "${month}월 ${day}일 $dayOfWeekInKorean"
    }
    Column {
        Spacer(Modifier.padding(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            // todo: 아이콘
            Text(
                text = displayText,
                fontFamily = suitFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = main_100
            )
        }
    }
}
