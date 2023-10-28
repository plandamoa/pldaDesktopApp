package datePickerDialog

import UI.main_100
import UI.suitFamily
import UI.text_primary
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DatePickerDialog(
    onDialogDismiss: () -> Unit,
    onConfirm: (year: Int, month: Int) -> Unit
) {
    var yearSelected by remember { mutableStateOf(2023) } // Default year
    var monthSelected by remember { mutableStateOf(10) } // Default month

    AlertDialog(
        modifier = Modifier.size(400.dp, 300.dp),
        onDismissRequest = {
            onDialogDismiss()
        },
        title = { },
        text = {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
            ) {
                DateTextRow()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    YearsMonthsPicker(
                        onYearSelected = { year ->
                            yearSelected = year
                        },
                        onMonthSelected = { month ->
                            monthSelected = month
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ConfirmButton {
                        onConfirm(yearSelected, monthSelected)
                        onDialogDismiss()
                    }
                }
            }
        },
        shape = RoundedCornerShape(16.dp),
        confirmButton = { }
    )
}

@Composable
fun ConfirmButton(onClick: () -> Unit) {
    Box(modifier = Modifier.size(24.dp)) {
        Icon(
            Icons.Default.Check,
            contentDescription = "Check",
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onClick),
            tint = main_100
        )
    }
}

@Composable
fun DateTextRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        TextFun("언제", main_100)
        TextFun(" 로 갈까요?", text_primary)
    }
}

@Composable
fun TextFun(text: String, color: Color) {
    Text(
        text = text,
        fontFamily = suitFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = color
    )
}