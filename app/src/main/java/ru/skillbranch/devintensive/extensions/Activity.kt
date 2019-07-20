package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToLong

fun Activity.hideKeyboard() {

    val imm: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0)

}

fun Activity.isKeyboardOpen(): Boolean {

    val rootView = findViewById<View>(android.R.id.content)
    val rect = Rect()

    rootView.getWindowVisibleDisplayFrame(rect)

    val heightDiff = rootView.height - rect.height()
    val a = TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_DIP , 50.toFloat(), this.resources.displayMetrics).roundToLong()
    
    return a < heightDiff
}

fun Activity.isKeyboardClosed(): Boolean {

    return this.isKeyboardOpen().not()
}