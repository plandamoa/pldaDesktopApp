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
import java.time.format.DateTimeParseException

@Composable
fun TitleInputField(titleText: String, contentText: String): String {
    var titleValue by remember { mutableStateOf("") }
    InputField(
        titleText = titleText,
        defaultText = "",
        onTextChanged = { titleValue = it },
        placeholderText = contentText
    )
    return titleValue
}

@Composable
fun DateInputField(
    titleText: String = "날짜",
    defaultText: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    onDateChange: (String) -> Unit
) {
    InputField(
        titleText = titleText,
        defaultText = defaultText,
        onTextChanged = { newText ->
            val normalizedDate = normalizeDateInput(newText)
            onDateChange(normalizedDate)
        },
        placeholderText = "yyyy-MM-dd or yyyy-M-d"
    )
}

@Composable
fun InputField(
    titleText: String,
    defaultText: String,
    onTextChanged: (String) -> Unit,
    placeholderText: String = ""
) {
    var textState = remember { mutableStateOf(TextFieldValue(defaultText)) }
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
                    onTextChanged(it.text)
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
                    text = placeholderText,
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

fun normalizeDateInput(dateStr: String): String {
    return try {
        val date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-M-d"))
        date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    } catch (e: DateTimeParseException) {
        dateStr // Return the original string if parsing fails
    }
}