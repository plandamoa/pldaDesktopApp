package Custom

import UI.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTopBar(
    onDismiss: () -> Unit,
    backButtonText: String,
    centerText: String,
    showRightButton: Boolean = true,
    onRightButtonClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        // Left: Back button and text
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .clickable(onClick = { onDismiss() })
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Arrow",
                modifier = Modifier.size(24.dp),
                tint = gray_40
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = backButtonText,
                fontFamily = suitFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = text_third
            )
        }

        // Center: Text
        Text(
            text = centerText,
            fontFamily = suitFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = text_primary,
            modifier = Modifier.align(Alignment.Center)
        )

        // Right: Complete button
        if (showRightButton) {
            Box(
                modifier = Modifier
                    .clickable(onClick = {
                        onRightButtonClick.invoke()
                        onDismiss.invoke()
                    })
                    .background(main_100, shape = RoundedCornerShape(8.dp))
                    .align(Alignment.TopEnd)
                    .size(width = 64.dp, height = 32.dp),
                contentAlignment = Alignment.Center  // Align inner components to center
            ) {
                Text(
                    text = "완료",
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}
