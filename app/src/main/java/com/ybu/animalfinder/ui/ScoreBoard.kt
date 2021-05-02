package com.ybu.animalfinder.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.ybu.animalfinder.R

class ScoreBoard : AppCompatActivity() {

    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_board)
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvBestPoint = findViewById<TextView>(R.id.tvBestPoint)


        val sharedPreferences = this.getSharedPreferences(packageName,android.content.Context.MODE_PRIVATE)
        val bestScore = sharedPreferences.getInt("Score",0)

        if(intent.extras != null){
            score = intent.extras!!.getInt("Score")
        }
        tvScore.text = score.toString()
        if(score > bestScore){
            sharedPreferences.edit().putInt("Score", score).apply()
            tvBestPoint.text = score.toString()
        } else{
            tvBestPoint.text = bestScore.toString()
        }
    }

    fun tryAgainClicked(view: View) {
        val intent = Intent(this, Level1::class.java)
        startActivity(intent);
        finish()
    }
}