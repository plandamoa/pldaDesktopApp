package UI

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

val suitFamily = FontFamily(
        Font(
                resource = "font/suit_semibold.ttf",
                weight = FontWeight.SemiBold
        ),
        Font(
                resource = "font/suit_extrabold.ttf",
                weight = FontWeight.ExtraBold
        ),
        Font(
                resource = "font/suit_medium.ttf",
                weight = FontWeight.Medium
        ),
)