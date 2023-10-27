package addScheduleScreen

import UI.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddScheduleScreen(onDismiss: () -> Unit) {
    var eventName by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg_white), // 뒷배경
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(bg_white), // 앞 메인 페이지
        ) {
            Column(
                Modifier.padding(horizontal = 150.dp)
                    .padding(bottom = 16.dp)
            ) {
                AddScheduleTopBar(
                    onDismiss = onDismiss,
                    onSave = {
                        addEventToDay(selectedDate, eventName) // passing the selected date here
                    },
                    isSaveButtonEnabled = eventName.isNotEmpty()
                )
                Spacer(modifier = Modifier.padding(32.dp))
                AddScheduleContent(
                    onEventNameChange = { newName ->
                        eventName = newName
                    },
                    onDateChange = { date ->
                        selectedDate = date
                    }
                )
            }
        }
    }
}

@Composable
fun AddScheduleTopBar(
    onDismiss: () -> Unit, onSave: () -> Unit,
    isSaveButtonEnabled: Boolean
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        // 왼쪽: 뒤로 가기 버튼과 '달력으로' 텍스트
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .clickable(onClick = { onDismiss() })
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Arrow",
                modifier = Modifier.size(24.dp),
                tint = gray_40
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "달력으로",
                fontFamily = suitFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = text_third
            )
        }

        // 가운데: '일정 추가' 텍스트
        Text(
            text = "일정 추가",
            fontFamily = suitFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = text_primary,
            modifier = Modifier.align(Alignment.Center)
        )

        // 오른쪽: 완료 버튼
        Box(
            modifier = Modifier
                .clickable(enabled = isSaveButtonEnabled, onClick = {
                    onDismiss()
                    onSave()
                })
                .background(if (isSaveButtonEnabled) main_100 else gray_20, shape = RoundedCornerShape(8.dp))
                .align(Alignment.TopEnd)
                .size(width = 64.dp, height = 32.dp),
            contentAlignment = Alignment.Center  // 내부 컴포넌트를 중앙에 배치
        ) {
            Text(
                text = "완료",
                fontFamily = suitFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = if (isSaveButtonEnabled) Color.White else Color.White
            )
        }
    }
}

@Composable
fun AddScheduleContent(
    onEventNameChange: (String) -> Unit,
    onDateChange: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    var selectedDate by remember {
        mutableStateOf(LocalDate.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
    }

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        val eventName = TitleInputField(
            titleText = "일정 이름",
            contentText = "제목을 입력해주세요."
        )
        onEventNameChange(eventName)
        Spacer(Modifier.padding(12.dp))
        ToggleMenu(
            icon = painterResource("image/category.svg"),
            titleText = "카테고리",
            items = listOf("기본", "개인", "학교", "회사"),
            showEditButton = true
        )
        Spacer(Modifier.padding(12.dp))
        DateInputField(
            titleText = "날짜 선택",
            onDateChange = { date ->
                selectedDate = date
                onDateChange(date)
            }
        )
        Spacer(Modifier.padding(12.dp))
        ToggleMenu(
            icon = painterResource("image/account.svg"),
            titleText = "연동된 계정",
            items = listOf("Facebook", "Github", "Google", "Kakao"),
            showEditButton = false
        )
        ToggleMenu(
            icon = painterResource("image/alarm.svg"),
            titleText = "알림",
            items = listOf("10분 전", "30분 전", "1시간 전", "하루 전"),
            showEditButton = false
        )
        ToggleMenu(
            icon = painterResource("image/alarm2.svg"),
            titleText = "두 번째 알림",
            items = listOf("10분 전", "30분 전", "1시간 전", "하루 전"),
            showEditButton = false
        )
        Spacer(Modifier.padding(12.dp))
        TitleInputField(
            titleText = "메모",
            contentText = "메모를 입력해주세요."
        )
        Spacer(Modifier.padding(24.dp))
    }
}