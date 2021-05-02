package com.ybu.animalfinder.ui

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ybu.animalfinder.R
import com.ybu.animalfinder.models.Animal
import com.ybu.animalfinder.utils.Constants
import java.util.*
import kotlin.collections.ArrayList

class Level3 : AppCompatActivity() , TextToSpeech.OnInitListener{

    lateinit var animals:ArrayList<Animal>
    lateinit var wantedAnimal: Animal
    lateinit var timer: CountDownTimer
    private var tts : TextToSpeech? = null
    private var mediaPlayer : MediaPlayer? = null
    private var counter: Int = 0
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level3)

        if (intent.extras != null) {
            score = intent.extras!!.getInt("Score")
            val tvScore = findViewById<TextView>(R.id.tvScore)
            tvScore.text = score.toString()
        }

        tts = TextToSpeech(this,this)
        prepareUI()
        setupTimer()
    }

    private fun setupTimer() {
        val tvTimer = findViewById<TextView>(R.id.tvTimer)
        timer = object: CountDownTimer(14000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                if (tts != null) {
                    tts!!.speak("Time is Up", TextToSpeech.QUEUE_FLUSH, null, "")
                }
                Toast.makeText(this@Level3,"Time is Up",Toast.LENGTH_LONG).show()
                val intent = Intent(this@Level3, ScoreBoard::class.java)
                intent.putExtra("Score", score)
                startActivity(intent);
                finish()
            }
        }
        timer.start()
    }

    private fun prepareUI(){
        animals = Constants.getAnimals()
        val animal = animals.random()
        animals.remove(animal)
        val animal2 = animals.random()
        animals.remove(animal2)
        val animal3 = animals.random();
        animals.remove(animal3)
        val animal4 = animals.random();

        val tvAnimal = findViewById<TextView>(R.id.tvAnimal)
        val imgFirstAnimal = findViewById<ImageView>(R.id.imgFirstAnimal)
        val imgSecondAnimal = findViewById<ImageView>(R.id.imgSecondAnimal)
        val imgThirdAnimal = findViewById<ImageView>(R.id.imgThirdAnimal)
        val imgForthAnimal = findViewById<ImageView>(R.id.imgForthAnimal)

        val randomlyGeneratedAnimals = arrayOf(animal,animal2,animal3,animal4)

        wantedAnimal =  randomlyGeneratedAnimals.random()

        tvAnimal.text = wantedAnimal.name

        imgFirstAnimal.setImageResource(animal.image!!)
        imgFirstAnimal.contentDescription = animal.name

        imgSecondAnimal.setImageResource(animal2.image!!)
        imgSecondAnimal.contentDescription = animal2.name

        imgThirdAnimal.setImageResource(animal3.image!!)
        imgThirdAnimal.contentDescription = animal3.name

        imgForthAnimal.setImageResource(animal4.image!!)
        imgForthAnimal.contentDescription = animal4.name

        if (tts != null) {
            tts!!.language = Locale.US
            tts!!.speak("Find the ${wantedAnimal.name}. ${wantedAnimal.name} makes sound: ", TextToSpeech.QUEUE_FLUSH, null, "")
            mediaPlayer = MediaPlayer.create(this@Level3, wantedAnimal.sound!!)
            mediaPlayer!!.isLooping = true;
            mediaPlayer!!.start()
        }

    }

    fun imgViewClicked(view: View) {

        timer.cancel()

        val imgView = view as ImageView
        if(imgView.contentDescription == wantedAnimal.name){

            if (tts != null) {
                tts!!.speak("You got it", TextToSpeech.QUEUE_FLUSH, null, "")
                val tvTimer = findViewById<TextView>(R.id.tvTimer)
                val time: Int = tvTimer.text.toString().toInt()
                score += Constants.LEVEL3_SCORE_SCALE * time
                val tvScore = findViewById<TextView>(R.id.tvScore)
                tvScore.text = score.toString()
                counter++
                Toast.makeText(this, "Your Score:" + score + "", Toast.LENGTH_LONG).show();
            }

            if (counter == 3) {
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, Level4::class.java)
                    intent.putExtra("Score", score)
                    startActivity(intent)
                    finish()
                }, 500)
            } else {
                Handler(Looper.getMainLooper()).postDelayed({
                    if (tts != null) {
                        tts!!.stop()
                    }
                    if (mediaPlayer != null) {
                        mediaPlayer!!.stop()
                    }
                    prepareUI()
                    setupTimer()
                }, 500)
            }
        }
        else{
            if (tts != null) {
                tts!!.speak("You selected wrong animal", TextToSpeech.QUEUE_FLUSH, null, "")
            }
            Handler(Looper.getMainLooper()).postDelayed({
                Toast.makeText(this, "Wrong Animal Selected", Toast.LENGTH_LONG).show();
                val intent = Intent(this, ScoreBoard::class.java)
                intent.putExtra("Score", score)
                startActivity(intent)
                finish()
            }, 1000)
        }
    }

    override fun onDestroy() {
        timer.cancel()
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if(mediaPlayer != null){
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
        }

        super.onDestroy()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            if (tts != null) {
                tts!!.language = Locale.US
                tts!!.speak("Find the ${wantedAnimal.name}. ${wantedAnimal.name} makes sound: ", TextToSpeech.QUEUE_FLUSH, null, "")
            }
        }
    }
}