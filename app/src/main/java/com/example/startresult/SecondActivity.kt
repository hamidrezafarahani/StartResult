package com.example.startresult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    companion object {
        private val tag: String by lazy {
            SecondActivity::class.java.name
        }

        fun newIntent(context: Context, value: Int?): Intent {
            return Intent(context, SecondActivity::class.java).apply {
                putExtra(tag, value)
            }
        }
    }

    private lateinit var outputData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val inputData = intent.getIntExtra(tag, 0)
        outputData = "out_".plus(inputData)

    }

    override fun onBackPressed() {
        setResult(RESULT_OK, Intent().apply {
            putExtra("RESULT", outputData)
        })
        super.onBackPressed()
    }
}