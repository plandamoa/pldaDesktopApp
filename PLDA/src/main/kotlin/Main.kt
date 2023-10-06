import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import java.awt.Toolkit

fun main() = application {
    // 스크린 크기 계산
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    val width = (screenSize.width * 0.8).toInt().dp
    val height = (screenSize.height * 0.8).toInt().dp


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
        AppScreen()
    }
}
