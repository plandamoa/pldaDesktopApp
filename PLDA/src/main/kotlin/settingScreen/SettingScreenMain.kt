package settingScreen

import Custom.CustomTopBar
import UI.*
import addScheduleScreen.RadioButtonGroup
import addScheduleScreen.ToggleMenu
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun SettingScreen(onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .background(bg_white) // 뒷배경 제거하거나 필요한 크기로 조절
            .zIndex(2f),  // zIndex 추가
    ) {
        Box(modifier = Modifier
            .padding(16.dp)
            .background(bg_white)
        ) {
            Column(
                Modifier.padding(horizontal = 150.dp)
                    .padding(bottom = 16.dp)
                    .fillMaxHeight()
            ) {
                CustomTopBar(
                    onDismiss = onDismiss,
                    backButtonText = "홈으로",
                    centerText = "설정",
                    showRightButton = false
                )
                Spacer(modifier = Modifier.padding(32.dp))
                SettingContent()
            }
        }
    }
}

@Composable
fun SettingContent() {
    Column() {
        UserContent()
        Spacer(modifier = Modifier.padding(16.dp))
        SettingContent(
            icon = painterResource("image/premium.svg"),
            titleText = "프리미엄 구독하기"
        )
        SettingContent(
            icon = painterResource("image/accountsynk.svg"),
            titleText = "계정 연동하기"
        )
        Divider(
            modifier = Modifier.padding(vertical = 16.dp),
            color = gray_20
        )
        SmallSettingContent("로그아웃")
        SmallSettingContent("회원탈퇴")
    }
}

@Composable
fun UserContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bg_gray, shape = RoundedCornerShape(16.dp))
            .padding(24.dp)
    ) {
        Text(
            text = "황당한코끼리",
            fontFamily = suitFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = text_primary
        )
    }
}

@Composable
fun SettingContent(
    icon: Painter,
    titleText: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .clickable(onClick = { }),
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
    }
}

@Composable
fun SmallSettingContent(titleText: String) {
    Box(modifier = Modifier.padding(vertical = 20.dp)) {
        Box(
            modifier = Modifier.clickable(onClick = { }),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = titleText,
                fontFamily = suitFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = text_third
            )
        }
    }
}
