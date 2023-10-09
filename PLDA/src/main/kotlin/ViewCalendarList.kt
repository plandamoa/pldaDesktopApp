import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import numberPicker.ScrolledNumberPickerUI

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun viewCalendarList(onDialogDismiss: () -> Unit) {
    AlertDialog(
            modifier = Modifier.size(600.dp, 300.dp),
            onDismissRequest = onDialogDismiss,
            title = { },
            text = {
                Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                ) {
                    ScrolledNumberPickerUI()
                }
            },
            confirmButton = {
                Button(onClick = onDialogDismiss) {
                    Text("OK")
                }
            }
    )
}
