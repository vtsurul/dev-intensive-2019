package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

fun Activity.hideKeyboard() {

    val imm: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0)

}

fun Activity.isKeyboardOpen(): Boolean {

    val rect = Rect()
    this.window.decorView.getWindowVisibleDisplayFrame(rect)

    val displayHeight = rect.bottom - rect.top
    val height = this.window.decorView.height

    return (displayHeight.toDouble() / height.toDouble()) > 0.8
}

fun Activity.isKeyboardClosed(): Boolean {

    val rect = Rect()
    this.window.decorView.getWindowVisibleDisplayFrame(rect)

    val displayHeight = rect.bottom - rect.top
    val height = this.window.decorView.height

    return ((displayHeight.toDouble() / height.toDouble()) > 0.8).not()

}