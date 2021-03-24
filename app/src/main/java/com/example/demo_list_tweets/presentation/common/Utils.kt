@file:Suppress("DEPRECATION")

package com.example.demo_list_tweets.presentation.common

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.demo_list_tweets.R

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

fun hideKeyboard (context: Context, view: View?) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    view?.let {
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun getRecycleViewDivider(context: Context): DividerItemDecoration {
    val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    ContextCompat.getDrawable(context, R.drawable.border)?.let {
        divider.setDrawable(it)
    }
    return divider
}