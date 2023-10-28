package addScheduleScreen

val events = mutableMapOf<String, MutableList<String>>(
    "2023-10-19" to mutableListOf("직장", "운동하기", "외출"),
    "2023-10-21" to mutableListOf("공부하기"),
    "2023-10-22" to mutableListOf("메일을 통해 내일 오후 2시에 진행될 프리코스 오리엔테이션 일정")
)

fun addEventToDay(date: String, event: String) {
    val currentEvents = events[date] ?: mutableListOf()
    currentEvents.add(event)
    events[date] = currentEvents
}