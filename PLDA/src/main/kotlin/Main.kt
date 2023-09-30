import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import java.awt.Toolkit

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Column {
            Button(onClick = {
                text = "Hello, Desktop!"
            }) {
                Text(text)
            }
        }
    }
}

fun main() = application {
    // 스크린 크기 계산
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    val width = (screenSize.width * 0.6).toInt().dp
    val height = (screenSize.height * 0.6).toInt().dp


    Window(
        onCloseRequest = ::exitApplication,
        resizable = false,
        state = WindowState(
            placement = WindowPlacement.Floating,
            position = WindowPosition(Alignment.Center),
            width = width,
            height = height
        ),
        title = "PLDA",
        icon = painterResource("plda.png"),
    ) {
        App()
    }
}
