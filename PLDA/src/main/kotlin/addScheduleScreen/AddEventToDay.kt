package addScheduleScreen

val events = mutableMapOf(
    19 to mutableListOf("직장", "운동하기", "외출"),
    21 to mutableListOf("공부하기"),
    22 to mutableListOf("메일을 통해 내일 오후 2시에 진행될 프리코스 오리엔테이션 일정")
)

fun addEventToDay(day: Int, event: String) {
    val currentEvents = events[day] ?: mutableListOf()
    currentEvents.add(event)
    events[day] = currentEvents
}

