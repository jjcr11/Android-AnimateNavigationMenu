package com.example.animatenavigationmenu

import android.view.View

data class AnimateMenuButton(
    val icon: Int?,
    val color: Int?,
    var listener: ((View) -> Unit)?
)
