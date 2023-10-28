package custom

import UI.suitFamily
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    color: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontFamily = suitFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign,
    )
}