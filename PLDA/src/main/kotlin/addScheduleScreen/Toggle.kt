package addScheduleScreen

import UI.*
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToggleMenu(
    icon: Painter,
    titleText: String,
    items: List<String>,
    showEditButton: Boolean = true
) {
    var isContentVisible by remember { mutableStateOf(false) }
    var selectedItems by remember { mutableStateOf(listOf<String>()) }

    // selectedItems 리스트의 항목을 연결하여 문자열로 변환합니다.
    val displaySelectedItems = when {
        selectedItems.isEmpty() -> " "
        selectedItems.size == 1 -> selectedItems.first()
        else -> "${selectedItems.first()} 외 ${selectedItems.size - 1}개"
    }
    Column {
        ToggleTopBar(
            icon = icon,
            titleText = titleText,
            toggleButtonText = displaySelectedItems.ifEmpty { " " },
            onToggleClick = { isContentVisible = !isContentVisible } // Explicitly name the lambda argument
        )
        if (isContentVisible) {
            ToggleContent(
                items = items,
                showEditButton = showEditButton,
                selectedItem = selectedItems
            ) { item ->
                selectedItems = if (selectedItems.contains(item)) {
                    selectedItems - item
                } else {
                    selectedItems + item
                }
            }
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
fun ToggleContent(
    items: List<String>,
    showEditButton: Boolean = true,
    selectedItem: List<String>,
    onSelectionChanged: (String) -> Unit
) {
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
            CheckboxGroup(items, selectedItem, onSelectionChanged) // Update this line
            Spacer(Modifier.padding(12.dp))

            if (showEditButton) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .clickable { }
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
}

@Composable
fun CheckboxGroup(
    items: List<String>,
    selectedItem: List<String>,
    onSelectionChanged: (String) -> Unit
) {
    val setSelectedItems = rememberUpdatedState(selectedItem)

    Column {
        items.forEach { item ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = setSelectedItems.value.contains(item),
                    onCheckedChange = {
                        if (it) { // 체크됐을 경우 항목을 추가
                            onSelectionChanged(item)
                        } else { // 체크 해제됐을 경우 항목을 제거
                            onSelectionChanged(item)
                        }
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = item, modifier = Modifier.clickable {
                    if (setSelectedItems.value.contains(item)) {
                        onSelectionChanged(item)
                    } else {
                        onSelectionChanged(item)
                    }
                })
            }
        }
    }
}