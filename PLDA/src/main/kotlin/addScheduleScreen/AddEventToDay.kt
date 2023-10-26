package addScheduleScreen

import mainCalendarScreen.events

fun addEventToDay(day: Int, event: String) {
    val currentEvents = events[day] ?: mutableListOf()
    currentEvents.add(event)
    events[day] = currentEvents
}

