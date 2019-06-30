package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{

    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {

    return when(date.time - this.time) {

        0.toLong(), SECOND * 1.toLong() -> "только что"

        in (SECOND * 1.toLong()) + 1 .. SECOND * 45.toLong() -> "несколько секунд назад"

        in (SECOND * 45.toLong()) + 1 .. SECOND * 75.toLong() -> "минуту назад"

        in (SECOND * 75.toLong()) + 1 .. MINUTE * 45.toLong() -> "${persuadeNumeral(date.time, this.time, TimeUnits.MINUTE)} назад"

        in (MINUTE * 45.toLong()) + 1 .. MINUTE * 75.toLong() -> "час назад"

        in (MINUTE * 75.toLong()) + 1 .. HOUR * 22.toLong() -> "${persuadeNumeral(date.time, this.time, TimeUnits.HOUR)} назад"

        in (HOUR * 22.toLong()) + 1 .. HOUR * 26.toLong() -> "день назад"

        in (HOUR * 26.toLong()) + 1 .. DAY * 360.toLong() -> "${persuadeNumeral(date.time, this.time, TimeUnits.DAY)} назад"

        else -> "более года назад"
    }
}

private fun persuadeNumeral(time1 : Long, time2 : Long, timeUnit: TimeUnits) : String {

    val timeDiff: Long = (time1 - time2) /
            when(timeUnit) {
                TimeUnits.MINUTE -> MINUTE
                TimeUnits.HOUR -> HOUR
                else -> DAY
            }

    return when(timeDiff)
    {
        1.toLong() -> "$timeDiff ${
                if(timeUnit == TimeUnits.MINUTE) "минуту" else if (timeUnit == TimeUnits.HOUR) "час" else "день"}"

        2.toLong(), 3.toLong(), 4.toLong() -> "$timeDiff ${
            if(timeUnit == TimeUnits.MINUTE) "минуты" else if (timeUnit == TimeUnits.HOUR) "часа" else "дня"}"

        else -> "$timeDiff ${
            if(timeUnit == TimeUnits.MINUTE) "минут" else if (timeUnit == TimeUnits.HOUR) "часов" else "дней"}"
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}