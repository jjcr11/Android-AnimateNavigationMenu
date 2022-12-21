package com.example.animatenavigationmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.animatenavigationmenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf(
            AnimateMenuButton(null, R.color.teal_200) {
                Toast.makeText(this, "Button 1", Toast.LENGTH_SHORT).show()
            },
            AnimateMenuButton(R.drawable.ic_launcher_foreground, R.color.purple_700) {
                Toast.makeText(this, "Button 2", Toast.LENGTH_SHORT).show()
            },
            AnimateMenuButton(R.drawable.ic_launcher_foreground, R.color.purple_200, null)
        )

        binding.am.buttons = list

        binding.am.setOnClickListener(2) {
            Toast.makeText(this, "Button 3", Toast.LENGTH_SHORT).show()
        }

        binding.fab.setOnClickListener {
            if (binding.am.open) binding.am.close() else binding.am.open()
        }
    }

    override fun onBackPressed() {
        if (binding.am.open) binding.am.close() else super.onBackPressed()
    }
}