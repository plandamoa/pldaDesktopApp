package addScheduleScreen

import UI.gray_20
import UI.suitFamily
import UI.text_lowEmphasis
import UI.text_primary
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
    titleText: String,
    contentText: String
): String {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    var isFocused by remember { mutableStateOf(false) }

    Column {
        Text(
            text = titleText,
            fontFamily = suitFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = text_primary
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            BasicTextField(
                value = textState.value,
                onValueChange = {
                    textState.value = it
                },
                textStyle = TextStyle(
                    color = text_primary,
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                ),
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .size(50.dp)
                    .padding(end = 48.dp).padding(vertical = 5.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused },
                singleLine = true
            )
            if (!isFocused && textState.value.text.isEmpty()) { // Use value property to get the underlying value
                Text(
                    text = contentText,
                    color = text_lowEmphasis,
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(end = 48.dp)
                        .padding(vertical = 5.dp)
                )
            }
        }
        Divider(color = gray_20)
    }
    return textState.value.text
}

@Composable
fun DateInputField(
    titleText: String = "날짜",
    defaultText: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    onDateChange: (String) -> Unit
) {
    val textState = remember { mutableStateOf(TextFieldValue(defaultText)) }
    var isFocused by remember { mutableStateOf(false) }

    Column {
        Text(
            text = titleText,
            fontFamily = suitFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = text_primary
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            BasicTextField(
                value = textState.value,
                onValueChange = {
                    textState.value = it
                    onDateChange(it.text) // notify date change
                },
                textStyle = TextStyle(
                    color = text_primary,
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                ),
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .size(50.dp)
                    .padding(end = 48.dp).padding(vertical = 5.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                singleLine = true
            )
            if (!isFocused && textState.value.text.isEmpty()) {
                Text(
                    text = defaultText,
                    color = text_lowEmphasis,
                    fontFamily = suitFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(end = 48.dp)
                        .padding(vertical = 5.dp)
                )
            }
        }
        Divider(color = gray_20)
    }
}