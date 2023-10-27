import addScheduleScreen.AddScheduleScreen
import androidx.compose.runtime.*
import loginScreen.LoginScreen
import mainCalendarScreen.AppUI
import settingScreen.SettingScreen

enum class Screen {
    LOGIN, MAIN, ADD_SCHEDULE, SETTING
}

@Composable
fun AppScreen() {
    var currentScreen by remember { mutableStateOf(Screen.LOGIN) }

    when (currentScreen) {
        Screen.LOGIN -> LoginScreen {
            currentScreen = Screen.MAIN
        }
        Screen.MAIN -> AppUI(
            onAddScheduleClick = { currentScreen = Screen.ADD_SCHEDULE },
            onSettingsClick = { currentScreen = Screen.SETTING } // 설정 버튼 클릭 시 설정 페이지로 이동
        )
        Screen.ADD_SCHEDULE -> AddScheduleScreen(
            onDismiss = { currentScreen = Screen.MAIN }
        )
        Screen.SETTING -> SettingScreen(
            onDismiss = { currentScreen = Screen.MAIN } // '홈으로' 버튼 클릭 시 메인 페이지로 이동
        )
        Screen.SETTING -> SettingScreen(
            onDismiss = { currentScreen = Screen.MAIN }
        )
    }
}