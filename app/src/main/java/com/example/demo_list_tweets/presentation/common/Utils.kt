package com.example.demo_list_tweets.presentation.common

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.widget.TextView
import android.widget.Toast

//https://www.rockandnull.com/livedata-observe-once/
class ValueWrapper<T>(private val value: T) {

    private var isConsumed = false

    fun get(): T? =
        if (isConsumed) {
            null
        } else {
            isConsumed = true
            value
        }
}

fun showErrorToast(context: Context, message: String) {
    val toast: Toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    val view = toast.view
    view?.background?.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
    val text = view?.findViewById<TextView>(android.R.id.message)
    text?.setTextColor(Color.WHITE)
    toast.show()
}