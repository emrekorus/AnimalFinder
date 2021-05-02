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
import kotlin.concurrent.schedule

class Level5 : AppCompatActivity() , TextToSpeech.OnInitListener {

    lateinit var animals:ArrayList<Animal>
    lateinit var wantedAnimal: Animal
    lateinit var timer: CountDownTimer
    private var tts : TextToSpeech? = null
    private var mediaPlayer : MediaPlayer? = null
    private var counter: Int = 0
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level5)

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
        timer = object: CountDownTimer(8000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                if (tts != null) {
                    tts!!.speak("Time is Up", TextToSpeech.QUEUE_FLUSH, null, "")
                }
                Toast.makeText(this@Level5,"Time is Up", Toast.LENGTH_LONG).show()
                val intent = Intent(this@Level5, ScoreBoard::class.java)
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
        animals.remove(animal4)
        val animal5 = animals.random();
        animals.remove(animal5)
        val animal6 = animals.random();
        animals.remove(animal6)
        val animal7 = animals.random();
        animals.remove(animal7)
        val animal8 = animals.random();

        val tvAnimal = findViewById<TextView>(R.id.tvAnimal)

        val imgFirstAnimal = findViewById<ImageView>(R.id.imgFirstAnimal)
        val imgSecondAnimal = findViewById<ImageView>(R.id.imgSecondAnimal)
        val imgThirdAnimal = findViewById<ImageView>(R.id.imgThirdAnimal)
        val imgForthAnimal = findViewById<ImageView>(R.id.imgForthAnimal)
        val imgFifthAnimal = findViewById<ImageView>(R.id.imgFifthAnimal)
        val imgSixthAnimal = findViewById<ImageView>(R.id.imgSixthAnimal)
        val imgSeventhAnimal = findViewById<ImageView>(R.id.imgSeventhAnimal)
        val imgEighthAnimal = findViewById<ImageView>(R.id.imgEighthAnimal)

        val randomlyGeneratedAnimals = arrayOf(animal,animal2,animal3,animal4,animal5,animal6,animal7,animal8)

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

        imgFifthAnimal.setImageResource(animal5.image!!)
        imgFifthAnimal.contentDescription = animal5.name

        imgSixthAnimal.setImageResource(animal6.image!!)
        imgSixthAnimal.contentDescription = animal6.name

        imgSeventhAnimal.setImageResource(animal7.image!!)
        imgSeventhAnimal.contentDescription = animal7.name

        imgEighthAnimal.setImageResource(animal8.image!!)
        imgEighthAnimal.contentDescription = animal8.name

        if (tts != null) {
            tts!!.language = Locale.US
            tts!!.speak("Find the ${wantedAnimal.name}. ${wantedAnimal.name} makes sound: ", TextToSpeech.QUEUE_FLUSH, null, "")
            mediaPlayer = MediaPlayer.create(this@Level5, wantedAnimal.sound!!)
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
                score += Constants.LEVEL5_SCORE_SCALE * time
                val tvScore = findViewById<TextView>(R.id.tvScore)
                tvScore.text = score.toString()
                counter++
                Toast.makeText(this, "Your Score:" + score + "", Toast.LENGTH_LONG).show();
            }

            if (counter == 3) {
                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, ScoreBoard::class.java)
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