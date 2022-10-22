package com.example.bitfit2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val saveFoodButton = findViewById<Button>(R.id.saveFoodBtn)

        saveFoodButton.setOnClickListener {
            val foodNameEditText = findViewById<EditText>(R.id.foodNameEditText).text.toString()
            val caloriesNumberEditText = findViewById<EditText>(R.id.caloriesEditText).text.toString()
            val caloriesStringTextView = findViewById<TextView>(R.id.caloriesTextView).text.toString()

            lifecycleScope.launch(IO) {
                (application as FoodApplication).db.foodDao().insert(
                    FoodEntity(foodNameEditText, caloriesNumberEditText, caloriesStringTextView)            )
            }

            val intent = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}