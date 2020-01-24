package com.byjus.headlines.assignment.samsruti.util

import android.view.View
import android.view.ViewGroup

fun View.setTopMargin(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}