package com.mary.mvichat.presentation.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT = "HH:mm"

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)

fun ViewGroup.inflateView(resource: Int): View = LayoutInflater.from(context).inflate(resource, this, false)

fun Date.toDateString() : String =
    SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(this)