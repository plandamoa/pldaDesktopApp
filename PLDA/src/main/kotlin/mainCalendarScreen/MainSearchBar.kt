package mainCalendarScreen

import UI.gray_10
import UI.suitFamily
import UI.text_third
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import custom.CustomText

@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
                .height(32.dp)
                .width(116.dp)
                .background(color = gray_10, shape = RoundedCornerShape(8.dp))
    ) {
        Icon(
            painterResource("image/search.svg"),
            contentDescription = "Search Icon",
            tint = text_third,
            modifier = Modifier
                    .padding(start = 24.dp, top = 8.dp)
                    .size(16.dp)
        )
        BasicTextField( // todo: 검색창 클릭 했을 떄 텍스트 입력 부분 어떻게 디자인 할지 논의
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(
                color = text_third,
                fontFamily = suitFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            ),
            cursorBrush = SolidColor(text_third),
            singleLine = true,
            modifier = Modifier
                    .padding(start = 40.dp, end = 16.dp) // Icon의 width와 padding을 고려
                    .fillMaxHeight()
                    .padding(vertical = 8.dp) // 수직 패딩을 조절하여 텍스트가 세로 중앙에 위치하도록 함
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused }
        )

        if (!isFocused && text.isEmpty()) { // 기본 텍스트
            Box(
                Modifier.padding(start = 48.dp, end = 24.dp)
                    .padding(vertical = 8.dp)
            ) {
                CustomText("일정검색", text_third, 12.sp, FontWeight.Medium)
            }
        }
    }
}