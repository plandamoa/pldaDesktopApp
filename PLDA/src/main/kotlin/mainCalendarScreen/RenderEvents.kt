package mainCalendarScreen

import UI.sub_skyblue_primary
import addScheduleScreen.events
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RenderEvents(year: Int, month: Int, day: Int) {
    val currentDate = "${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}"
    events[currentDate]?.take(3)?.forEach { eventName ->
        EventBox(eventName, sub_skyblue_primary, year, month, day)
        Spacer(Modifier.padding(1.dp))
    }
}