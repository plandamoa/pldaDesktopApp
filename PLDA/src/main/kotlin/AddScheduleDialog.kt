import UI.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddScheduleDialog(onDialogDismiss: () -> Unit) {
    AlertDialog(
        modifier = Modifier.size(500.dp, 700.dp),
        onDismissRequest = onDialogDismiss,
        title = { },
        text = {
            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                AddScheduleTopBar()
                Spacer(modifier = Modifier.padding(32.dp))
                Box(
                    modifier = Modifier.weight(1f).fillMaxWidth()
                ) {
                    AddScheduleContent()
                }
            }
        },
        shape = RoundedCornerShape(16.dp),
        confirmButton = { }
    )
}

@Composable
fun AddScheduleTopBar() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // 왼쪽: 뒤로 가기 버튼과 '달력으로' 텍스트
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart) // 왼쪽 정렬
                .clickable(onClick = { }),
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
                .clickable(onClick = { })
                .background(main_100, shape = RoundedCornerShape(8.dp))
                .align(Alignment.CenterEnd)
                .size(width = 64.dp, height = 32.dp),
            contentAlignment = Alignment.Center  // 내부 컴포넌트를 중앙에 배치
        ) {
            Text(
                text = "완료",
                fontFamily = suitFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Color.White  // 버튼의 글자 색을 흰색으로 변경
            )
        }
    }
}

@Composable
fun AddScheduleContent() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        TextFieldComponent() // 일정 이름
        Toggle() // 카테고리
        Toggle() // 시간
        Toggle() // 연동된 계정
        Toggle() // 알림
        Toggle() // 두 번째 알림
        TextFieldComponent() // 메모
        Toggle() // 알림
        Toggle() // 두 번째 알림
        TextFieldComponent() // 메모
    }
}

@Composable
fun TextFieldComponent() {
    var textState = remember { mutableStateOf(TextFieldValue()) }
    var isFocused by remember { mutableStateOf(false) }

    Column {
        Text(
            text = "일정 이름",
            fontFamily = suitFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = text_primary
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            BasicTextField(
                value = textState.value,
                onValueChange = {
                    textState.value = it
                },
                textStyle = TextStyle(
                    color = text_primary,
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                ),
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .size(50.dp)
                    .padding(end = 48.dp).padding(vertical = 5.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused },
                singleLine = true
            )
            if (!isFocused && textState.value.text.isEmpty()) { // Use value property to get the underlying value
                Text(
                    text = "제목을 입력해주세요.",
                    color = text_lowEmphasis,
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(end = 48.dp)
                        .padding(vertical = 5.dp)
                )
            }
        }
        Divider(color = gray_20)
    }
    Spacer(modifier = Modifier.padding(24.dp))
}

@Composable
fun Toggle() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // 왼쪽: 아이콘과 요소 제목
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Arrow",
                modifier = Modifier.size(24.dp),
                tint = gray_100
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "카테고리",
                fontFamily = suitFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = text_primary
            )
        }

        // 오른쪽: 토글 버튼
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(onClick = { }),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "학교 외 1",
                fontFamily = suitFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = text_secondary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painterResource("image/expand_more.svg"),
                contentDescription = "Down Arrow",
                modifier = Modifier.size(10.dp),
                tint = gray_60
            )
        }
    }
    Spacer(modifier = Modifier.padding(24.dp))
}