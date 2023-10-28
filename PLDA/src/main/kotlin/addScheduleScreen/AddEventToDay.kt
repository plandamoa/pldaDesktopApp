package addScheduleScreen

val events = mutableMapOf<String, MutableList<String>>(
    "2023-10-19" to mutableListOf("알고리즘 공부", "헬스장 PT", "외출"),
    "2023-10-21" to mutableListOf("프로그래밍 과제", "카공"),
    "2023-10-22" to mutableListOf("외부 대회 회의", "장 보기"),
    "2023-10-04" to mutableListOf("대외활동 1차 발표"),
    "2023-12-02" to mutableListOf("저녁 약속", "운동하기", "외출"),
    "2023-12-10" to mutableListOf("치과", "친구 만나기"),
    "2023-12-15" to mutableListOf("자격증 시험 점수 공개"),
    "2023-12-13" to mutableListOf("종강총회")
)

fun addEventToDay(date: String, event: String) {
    val currentEvents = events[date] ?: mutableListOf()
    currentEvents.add(event)
    events[date] = currentEvents
}