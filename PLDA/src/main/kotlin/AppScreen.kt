import androidx.compose.runtime.*
import loginScreen.LoginScreen
import mainCalendarScreen.AppUI

enum class Screen {
    LOGIN, MAIN
}

@Composable
fun AppScreen() {
    var currentScreen by remember { mutableStateOf(Screen.LOGIN) }

    when (currentScreen) {
        Screen.LOGIN -> LoginScreen {
            currentScreen = Screen.MAIN
        }
        Screen.MAIN -> AppUI()
    }
}