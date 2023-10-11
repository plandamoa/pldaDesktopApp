import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import java.awt.Toolkit

fun main() = application {
    // 스크린 크기 계산
    val screenSize = Toolkit.getDefaultToolkit().screenSize

    val width = (screenSize.width * 0.8).dp
    val height = (screenSize.height * 0.8).dp

    val minWidth = 800
    val minHeight = 600

    Window(
        onCloseRequest = ::exitApplication,
        resizable = true,
        state = WindowState(
            placement = WindowPlacement.Floating,
            position = WindowPosition(Alignment.Center),
            width = width,
            height = height
        ),
        title = "PLDA",
        icon = painterResource("image/plda.png"),
    ) {
        // 여기서 AWT 윈도우의 참조를 얻고, 최소 크기를 설정합니다.
        val awtWindow = this.window
        awtWindow.minimumSize = java.awt.Dimension(minWidth, minHeight)

        AppScreen()
    }
}
