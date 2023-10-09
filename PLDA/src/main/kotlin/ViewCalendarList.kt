import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import numberPicker.MainActivityUI

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun viewCalendarList(onDialogDismiss: () -> Unit) {
    AlertDialog(
            modifier = Modifier.size(600.dp, 400.dp),
            onDismissRequest = onDialogDismiss,
            title = { },
            text = { MainActivityUI() },
            confirmButton = {
                Button(onClick = onDialogDismiss) {
                    Text("OK")
                }
            }
    )
}
