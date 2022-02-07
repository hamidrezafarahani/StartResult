package com.example.startresult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.startresult.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var ac: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setListeners()
    }

    private fun init() {
        ac = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            this@MainActivity toast if (it.resultCode == RESULT_OK)
                it.data?.extras?.getString("RESULT") ?: "NULL"
            else "Result not found"
        }
    }

    private fun setListeners() {
        binding.btnNext.setOnClickListener {
            ac.launch(SecondActivity.newIntent(this@MainActivity, 3))
        }
    }

    private infix fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}