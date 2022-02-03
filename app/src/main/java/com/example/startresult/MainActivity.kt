package com.example.startresult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import com.example.startresult.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setListeners()
    }

    private fun init() {
        activityResultLauncher = registerForActivityResult(contract, callback)
    }

    private fun setListeners() {
        binding.btnNext.setOnClickListener {
            activityResultLauncher.launch(3)
        }
    }

    private val contract = object : ActivityResultContract<Int, String>() {
        override fun createIntent(context: Context, input: Int?): Intent {
            return SecondActivity.newIntent(this@MainActivity, input)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): String {
            return if (resultCode == RESULT_OK)
                intent?.getStringExtra("RESULT") ?: "NULL"
            else "Result Not Found!!"
        }
    }

    private val callback = ActivityResultCallback<String> { result ->
        Toast.makeText(this@MainActivity, result.toString(), Toast.LENGTH_SHORT).show()
    }
}