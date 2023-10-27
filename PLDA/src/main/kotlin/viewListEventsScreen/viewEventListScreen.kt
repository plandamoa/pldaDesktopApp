package viewListEventsScreen

import UI.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewEventListDialog(onDialogDismiss: () -> Unit) {
    AlertDialog(
        modifier = Modifier
            .size(500.dp, 500.dp),
        onDismissRequest = onDialogDismiss,
        title = { TopText() },
        text = {
            Column {
                Text(
                    text = "",
                    fontSize = 1.sp
                )
                EventViewBox()
                EventViewBox()
            }
        },
        shape = RoundedCornerShape(16.dp),
        confirmButton = { },
        backgroundColor = bg_gray
    )
}

@Composable
fun TopText() {
    Column() {
        Spacer(Modifier.padding(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            // todo: 아이콘
            Text(
                text = "10월 26일 수요일 (오늘)",
                fontFamily = suitFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = main_100
            )
        }
    }
}

@Composable
fun EventViewBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bg_white, shape = RoundedCornerShape(8.dp))
            .padding(24.dp)
    ) {
        Column {
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(
                    text = "from 구글캘린더",
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = text_third
                )
            }
            Text(
                text = "공과금 자동이체",
                fontFamily = suitFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = text_primary
            )
            Text(
                text = "10:00",
                fontFamily = suitFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                color = text_secondary
            )
        }
    }
    Spacer(Modifier.padding(4.dp))
}