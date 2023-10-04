
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
    ) {
        Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
        ) {
            // 로고 표시
            Image(
                    painter = painterResource("plda.png"),
                    contentDescription = "PLDA Logo",
                    modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 로고 아래 설명
            Text(text = "PLDA : 플랜다모아", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(24.dp))

            // SNS 로그인 버튼
            Button(onClick = onLoginSuccess) {
                Text("SNS로 로그인")
            }
        }
    }
}

