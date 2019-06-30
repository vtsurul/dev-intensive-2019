package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName:String?):Pair<String?, String?>{

        val parts : List<String>? = (
            if (fullName != null)
                fullName!!.trim()
            else
                fullName)?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        if(firstName == "" || firstName == " ") {
            firstName = null
            lastName = null
        }

        //return Pair(firstName, lastName)

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {

        var result = ""

        payload.toLowerCase().toCharArray().forEach {

            when (it) {
                'а' -> result += "a"
                'б' -> result += "b"
                'в' -> result += "v"
                'г' -> result += "g"
                'д' -> result += "d"
                'е' -> result += "e"
                'ё' -> result += "e"
                'ж' -> result += "zh"
                'з' -> result += "z"
                'и' -> result += "i"
                'й' -> result += "i"
                'к' -> result += "k"
                'л' -> result += "l"
                'м' -> result += "m"
                'н' -> result += "n"
                'о' -> result += "o"
                'п' -> result += "p"
                'р' -> result += "r"
                'с' -> result += "s"
                'т' -> result += "t"
                'у' -> result += "u"
                'ф' -> result += "f"
                'х' -> result += "h"
                'ц' -> result += "ts"
                'ч' -> result += "ch"
                'ш' -> result += "sh"
                'щ' -> result += "sh"
                'ъ' -> result += ""
                'ы' -> result += "i"
                'ь' -> result += ""
                'э' -> result += "e"
                'ю' -> result += "yu"
                'я' -> result += "ya"
                else -> result += "$divider"
            }
        }

        var capitalizedResult = ""

        result.split(divider).forEach {
            capitalizedResult += "$divider${it.capitalize()}"
        }

        return capitalizedResult.substring(divider.length)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        val firstInitial = when (firstName) {
            "", " ", null -> null
            else -> firstName.trim().toUpperCase().substring(0,1)}

        val lastInitial = when (lastName) {
            "", " ", null -> null
            else -> lastName.trim().toUpperCase().substring(0,1)}

        if (firstInitial != null && lastInitial != null)
            return "$firstInitial$lastInitial"

        if (firstInitial != null)
            return "$firstInitial"

        if (lastInitial != null)
            return "$lastInitial"

        return null
    }
}