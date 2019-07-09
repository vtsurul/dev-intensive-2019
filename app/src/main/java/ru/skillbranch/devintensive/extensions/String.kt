package ru.skillbranch.devintensive.extensions

fun String.truncate(size: Int = 16): String {

    if (this.trimEnd().length < size)
        return this.trimEnd()
    else
        return "${this.substring(0, size).trimEnd()}..."
}