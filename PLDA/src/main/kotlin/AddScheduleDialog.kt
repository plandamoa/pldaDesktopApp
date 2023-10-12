import UI.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddScheduleDialog(onDialogDismiss: () -> Unit) {
    AlertDialog(
        modifier = Modifier.size(400.dp, 700.dp),
        onDismissRequest = onDialogDismiss,
        title = { },
        text = {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                AddScheduleTopBar()
                AddScheduleContent()
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 여기에 화면을 꽉 채우는 다른 UI 컴포넌트들을 추가할 수 있습니다.
        Text("여기에 내용을 추가하세요.")
    }
}