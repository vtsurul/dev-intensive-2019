package ru.skillbranch.devintensive.utils

import kotlin.text.StringBuilder

object Utils {

    private val translitMap = mapOf(
        'а' to "a", 'б' to "b", 'в' to "v", 'г' to "g", 'д' to "d", 'е' to "e", 'ё' to "e", 'ж' to "zh", 'з' to "z",
        'и' to "i", 'й' to "i", 'к' to "k", 'л' to "l", 'м' to "m", 'н' to "n", 'о' to "o", 'п' to "p", 'р' to "r",
        'с' to "s", 'т' to "t", 'у' to "u", 'ф' to "f", 'х' to "h", 'ц' to "ts", 'ч' to "ch", 'ш' to "sh", 'щ' to "sh'",
        'ъ' to "", 'ы' to "i", 'ь' to "", 'э' to "e", 'ю' to "yu", 'я' to "ya")

    fun parseFullName(fullName:String?):Pair<String?, String?>{

        val parts : List<String>? = (fullName?.trim() ?: fullName)?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        if(firstName == "" || firstName == " ") {
            firstName = null
            lastName = null
        }

        //return Pair(firstName, lastName)

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " ") = buildString {

        payload.forEach {
            this.append(
                when(it) {
                    ' ' -> divider
                    else -> translitMap[it] ?: translitMap[it.toLowerCase()]?.capitalize() ?: it.toString()
                }
            )
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        val firstInitial = when (firstName) {
            "", " ", null -> null
            else -> firstName.trim().toUpperCase().substring(0, 1)}

        val lastInitial = when (lastName) {
            "", " ", null -> null
            else -> lastName.trim().toUpperCase().substring(0, 1)}

        if (firstInitial != null && lastInitial != null)
            return "$firstInitial$lastInitial"

        if (firstInitial != null)
            return "$firstInitial"

        if (lastInitial != null)
            return "$lastInitial"

        return null
    }
}