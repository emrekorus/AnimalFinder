package com.ybu.animalfinder.ui

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
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

class Level4 : AppCompatActivity() , TextToSpeech.OnInitListener{

    lateinit var animals:ArrayList<Animal>
    lateinit var wantedAnimal: Animal
    lateinit var timer: CountDownTimer
    private var tts : TextToSpeech? = null
    private var mediaPlayer : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level4)

        tts = TextToSpeech(this,this)
        prepareUI()
        setupTimer()
    }

    private fun setupTimer() {
        val tvTimer = findViewById<TextView>(R.id.tvTimer)
        timer = object: CountDownTimer(11000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@Level4,"Time is Up",Toast.LENGTH_LONG).show()
                val intent = Intent(this@Level4, ScoreBoard::class.java)
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

        val tvAnimal = findViewById<TextView>(R.id.tvAnimal)
        val imgFirstAnimal = findViewById<ImageView>(R.id.imgFirstAnimal)
        val imgSecondAnimal = findViewById<ImageView>(R.id.imgSecondAnimal)
        val imgThirdAnimal = findViewById<ImageView>(R.id.imgThirdAnimal)
        val imgForthAnimal = findViewById<ImageView>(R.id.imgForthAnimal)
        val imgFifthAnimal = findViewById<ImageView>(R.id.imgFifthAnimal)
        val imgSixthAnimal = findViewById<ImageView>(R.id.imgSixthAnimal)

        val randomlyGeneratedAnimals = arrayOf(animal,animal2,animal3,animal4,animal5,animal6)

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
    }

    fun imgViewClicked(view: View) {

        timer.cancel()

        val imgView = view as ImageView
        if(imgView.contentDescription == wantedAnimal.name){
            val intent = Intent(this, Level5::class.java)
            startActivity(intent);
            finish()
        }
        else{

            Toast.makeText(this,"Wrong Animal Selected",Toast.LENGTH_LONG).show();
            val intent = Intent(this, ScoreBoard::class.java)
            startActivity(intent);
            finish()
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
        if (status == TextToSpeech.SUCCESS){
            tts!!.language = Locale.US
            tts!!.speak("Find the ${wantedAnimal.name}. ${wantedAnimal.name} makes sound: ", TextToSpeech.QUEUE_FLUSH, null, "")
            mediaPlayer = MediaPlayer.create(this@Level4,wantedAnimal.sound!!)
            mediaPlayer!!.start()
        }
    }
}