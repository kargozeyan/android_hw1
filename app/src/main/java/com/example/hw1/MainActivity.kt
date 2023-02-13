package com.example.hw1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hw1.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val converter = IntegerToTextConverter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConvert.setOnClickListener {
            binding.result.text = ""
            if (binding.numberInput.text.toString().isEmpty()) {
                toast("Input the number")
                return@setOnClickListener
            }

            val input = binding.numberInput.text.toString()

            try {
                val convert = converter.convert(input)
                binding.result.text = convert
            } catch (e: ConvertException) {
                toast(e.message!!)
            }
        }
    }

    private fun toast(text:String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
    }
}