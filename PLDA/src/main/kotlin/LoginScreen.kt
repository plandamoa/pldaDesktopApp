
import UI.dateBlack
import UI.suitFamily
import UI.text_primary
import UI.text_third
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    val svgImage = painterResource("image/Google_Kor.svg")

    Box(
            modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
            contentAlignment = Alignment.Center,

    ) {
        Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
        ) {
            // 로고
            Image(
                    painter = painterResource("image/plda.png"),
                    contentDescription = "PLDA Logo",
                    modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 앱 이름
            Text(
                text = "PLDA",
                fontSize = 32.sp,
                fontFamily = suitFamily,
                fontWeight = FontWeight.ExtraBold,
                color = text_primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 설명
            Text(
                text = "여기저기 흩어진 내 일정을 단 한번에",
                fontSize = 16.sp,
                fontFamily = suitFamily,
                fontWeight = FontWeight.Medium,
                color = text_third
            )

            Spacer(modifier = Modifier.height(24.dp))

            // SNS 로그인 버튼
            val aspectRatio = 5f //이미지 비율
            Box(
                    modifier = Modifier
                            .width(200.dp)
                            .aspectRatio(aspectRatio)
                            .clickable(onClick = onLoginSuccess)
            ) {
                Image(
                        painter = svgImage,
                        contentDescription = "Login with SVG",
                        modifier = Modifier.fillMaxSize()
                )
            }
        }

        Box(
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp)
        ) {
            // Copyright Text
            Text(
                text = "Copyright © PLDA team. All rights reserved.",
                fontSize = 12.sp,
                fontFamily = suitFamily,
                fontWeight = FontWeight.Medium,
                color = text_third
            )
        }
    }
}