import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun viewCalendarList(onDialogDismiss: () -> Unit) {


    AlertDialog(
            modifier = Modifier.size(600.dp, 400.dp),
            onDismissRequest = onDialogDismiss,
            title = { },
            text = {
            },
            confirmButton = {
                Button(onClick = onDialogDismiss) {
                    Text("OK")
                }
            }
    )
}
