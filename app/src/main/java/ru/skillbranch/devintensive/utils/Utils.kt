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
        return payload
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