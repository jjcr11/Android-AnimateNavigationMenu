package com.example.animatenavigationmenu

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.animatenavigationmenu.databinding.FragmentAnimateMenuBinding

class AnimateMenuFragment(
    private val button: AnimateMenuButton,
    private val iconSize: Float
) : Fragment() {

    private lateinit var binding: FragmentAnimateMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimateMenuBinding.inflate(inflater, container, false)

        if (button.icon != null) {
            binding.iv.setImageResource(button.icon)
        }

        val layoutParams = FrameLayout.LayoutParams(
            iconSize.toInt(),
            iconSize.toInt(),
        )
        layoutParams.gravity = Gravity.CENTER

        binding.iv.layoutParams = layoutParams

        if (button.color != null) {
            binding.root.setBackgroundColor(resources.getColor(button.color, null))
        }

        binding.root.setOnClickListener(button.listener)

        return binding.root
    }
}