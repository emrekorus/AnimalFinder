package com.ybu.animalfinder.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ybu.animalfinder.R

class ScoreBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_board)
    }

    fun tryAgainClicked(view: View) {
        val intent = Intent(this, Level1::class.java)
        startActivity(intent);
        finish()
    }
}