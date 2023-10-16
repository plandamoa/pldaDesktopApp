package addScheduleScreen

import UI.*
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToggleMenu(
    icon: Painter,
    titleText: String,
    toggleButtonText: String
) {
    var isContentVisible by remember { mutableStateOf(false) }

    Column {
        ToggleTopBar(icon, titleText, toggleButtonText)
            { isContentVisible = !isContentVisible }
        if (isContentVisible) {
            ToggleContent()
        }
    }
}

@Composable
fun ToggleTopBar(
    icon: Painter,
    titleText: String,
    toggleButtonText: String,
    onToggleClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .clickable(onClick = onToggleClick),
        contentAlignment = Alignment.Center

    ) {
        // 왼쪽: 아이콘과 요소 제목
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = "Icon",
                modifier = Modifier.size(24.dp),
                tint = gray_100
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = titleText,
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
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = toggleButtonText,
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
}

@Composable
fun ToggleContent() {
    val items = listOf("기본", "개인", "학교", "회사")
    val isVisible = remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible.value,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {

    }
    Box(
        modifier = Modifier
        .fillMaxWidth()
        .background(bg_gray, shape = RoundedCornerShape(16.dp))
        .padding(24.dp)
    ) {
        Column {
            RadioButtonGroup(items) { selectedItem ->
                println("Selected item: $selectedItem")
            }
            Spacer(Modifier.padding(12.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .clickable {  }
                        .padding(8.dp)
                        .align(Alignment.CenterEnd),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "카테고리 편집",
                        fontFamily = suitFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = text_third
                    )
                    Spacer(modifier = Modifier.width(1.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Arrow",
                        modifier = Modifier.size(16.dp),
                        tint = text_third
                    )
                }
            }
        }
    }
}

@Composable
fun RadioButtonGroup(
    items: List<String>,
    onSelectionChanged: (String) -> Unit
) {
    val (selectedItem, setSelectedItem) = remember { mutableStateOf<String?>(null) }

    Column {
        items.forEach { item ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = item == selectedItem,
                    onClick = {
                        setSelectedItem(item)
                        onSelectionChanged(item)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = item, modifier = Modifier.clickable { setSelectedItem(item) })
            }
        }
    }
}