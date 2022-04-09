package com.example.studentapi.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.util.*


fun CharSequence?.isValidEmail() = !isNullOrBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

//fun hideKeyboard(activity: Activity) {
//    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//    val focusedView = activity.currentFocus ?: View(activity)
//    imm.hideSoftInputFromWindow(focusedView.windowToken, 0)
//}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Fragment.navigateTo(@IdRes id: Int) {
    findNavController().navigate(id)
    hideKeyboard()
}

fun Fragment.navigateTo(action: NavDirections) {
    findNavController().navigate(action)
    hideKeyboard()
}

fun View.navigateTo(action: NavDirections) {
    findNavController().navigate(action)
    this.context.hideKeyboard(this)
}

fun Fragment.popBackStack() {
    findNavController().popBackStack()
    hideKeyboard()
}

@SuppressLint("SimpleDateFormat")
fun String.parseDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val outputFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
    val date: Date = inputFormat.parse(this)
    return outputFormat.format(date)
}