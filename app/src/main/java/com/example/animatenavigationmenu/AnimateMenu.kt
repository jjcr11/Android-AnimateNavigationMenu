package com.example.animatenavigationmenu

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AnimateMenu(
    context: Context,
    attributeSet: AttributeSet
) : LinearLayout(context, attributeSet) {

    var buttons: List<AnimateMenuButton> = listOf()
    var open = false
    private val typedArray = context.theme.obtainStyledAttributes(
        attributeSet,
        R.styleable.AnimateMenu,
        0,
        0
    )
    private val iconSize = typedArray.getDimension(
        R.styleable.AnimateMenu_icon_size,
        200f
    )
    private val imageSize = typedArray.getDimension(
        R.styleable.AnimateMenu_image_size,
        150f
    )

    fun open() = CoroutineScope(Dispatchers.Main).launch {
        for (button in buttons) {
            val frameLayout = FrameLayout(context)
            frameLayout.id = View.generateViewId()
            frameLayout.layoutParams = FrameLayout.LayoutParams(
                iconSize.toInt(),
                iconSize.toInt()
            )
            getFragmentManager(context)!!.beginTransaction()
                .setCustomAnimations(
                    R.animator.animate_menu_button_1,
                    R.animator.animate_menu_button_2,
                    R.animator.animate_menu_button_3,
                    R.animator.animate_menu_button_4
                )
                .add(frameLayout.id, AnimateMenuFragment(button, imageSize))
                .addToBackStack(null)
                .commit()
            super.addView(frameLayout)
            delay(100)
        }
        open = true
    }

    fun close() = CoroutineScope(Dispatchers.Main).launch {
        for (index in buttons) {
            getFragmentManager(context)!!.popBackStack()
            delay(100)
        }
        delay(100)
        super.removeAllViews()
        open = false
    }

    fun setOnClickListener(index: Int, listener: ((View) -> Unit)?) {
        buttons[index].listener = listener
    }

    private fun getFragmentManager(context: Context?): FragmentManager? {
        return when (context) {
            is AppCompatActivity -> context.supportFragmentManager
            is ContextThemeWrapper -> getFragmentManager(context.baseContext)
            else -> null
        }
    }
}