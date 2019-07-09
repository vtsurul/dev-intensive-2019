package ru.skillbranch.devintensive.extensions

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

    val diff: Long = (date.time - this.time)

    return if ( diff >= 0) {

        when (diff) {
            0.sec, 1.sec -> "только что"
            in 2.sec..45.sec -> "несколько секунд назад"
            in 46.sec..75.sec -> "минуту назад"
            in 76.sec..45.min -> "${TimeUnits.MINUTE.plural(diff/MINUTE)} назад"
            in 45.min + 1..75.min -> "час назад"
            in 75.min + 1..22.hour -> "${TimeUnits.HOUR.plural(diff/HOUR)} назад"
            in 22.hour + 1..26.hour -> "день назад"
            in 26.hour + 1..360.day -> "${TimeUnits.DAY.plural(diff/DAY)} назад"
            else -> "более года назад"
        }

    } else {

        when (- diff) {
            0.sec, 1.sec -> "сейчас"
            in 2.sec .. 45.sec -> "через несколько секунд"
            in 46.sec .. 75.sec -> "через минуту"
            in 76.sec .. 45.min -> "через ${TimeUnits.MINUTE.plural(diff/ MINUTE)}"
            in 45.min + 1 .. 75.min -> "через час"
            in 75.min + 1 .. 22.hour -> "через ${TimeUnits.HOUR.plural(diff/HOUR)}"
            in 22.hour + 1 .. 26.hour -> "через день"
            in 26.hour + 1 .. 360.day -> "через ${TimeUnits.DAY.plural(diff/DAY)}"
            else -> "более чем через год"
        }
    }
}

enum class TimeUnits
{
    SECOND, MINUTE, HOUR, DAY;

    fun plural(value: Long) :String {

        return "$value ${pluralMap()[
                when (value.toInt()) {
                    1 -> "один"
                    2, 3, 4 -> "мало"
                    else -> "много"
                }]}"
    }

    private fun pluralMap(): Map<String, String> = when(this) {
        SECOND -> mapOf("один" to "секунда", "мало" to "секунды", "много" to "секунд")
        MINUTE -> mapOf("один" to "минута", "мало" to "минуты", "много" to "минут")
        HOUR -> mapOf("один" to "час", "мало" to "часа", "много" to "часов")
        DAY -> mapOf("один" to "день", "мало" to "дня", "много" to "дней")
    }
}

val Int.sec get() = this * SECOND
val Int.min get() = this * MINUTE
val Int.hour get() = this * HOUR
val Int.day get() = this * DAY