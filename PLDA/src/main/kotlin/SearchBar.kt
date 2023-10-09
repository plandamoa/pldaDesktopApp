import UI.searchBackGray
import UI.searchGray
import UI.suitFamily
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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