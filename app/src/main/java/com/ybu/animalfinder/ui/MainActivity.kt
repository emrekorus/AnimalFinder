package com.ybu.animalfinder.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.ybu.animalfinder.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = this.getSharedPreferences(packageName, android.content.Context.MODE_PRIVATE)
        val bestScore = sharedPreferences.getInt("Score", 0)

        val tvBestPoint = findViewById<TextView>(R.id.tvBestPoint)
        tvBestPoint.text = bestScore.toString()
    }

    fun playGameClicked(view: View) {
        val intent = Intent(this, Level1::class.java)
        startActivity(intent);
        finish()
    }
}