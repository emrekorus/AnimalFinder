package com.ybu.animalfinder.ui
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
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

class Level1 : AppCompatActivity(),TextToSpeech.OnInitListener{
    lateinit var animals:ArrayList<Animal>
    lateinit var wantedAnimal: Animal
    lateinit var timer: CountDownTimer
    private var tts : TextToSpeech? = null
    private var mediaPlayer : MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level1)

        tts = TextToSpeech(this,this)
        prepareUI()
        setupTimer()
    }

    private fun setupTimer() {
        val tvTimer = findViewById<TextView>(R.id.tvTimer)
        timer = object: CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@Level1,"Time is Up",Toast.LENGTH_LONG).show()
                val intent = Intent(this@Level1, ScoreBoard::class.java)
                startActivity(intent);
                finish()
            }
        }
        timer.start()
    }

    private fun prepareUI(){
        animals = Constants.getAnimals()
        val animal = animals.random();
        animals.remove(animal)
        val animal2 = animals.random();

        val tvAnimal = findViewById<TextView>(R.id.tvAnimal)
        val imgFirstAnimal = findViewById<ImageView>(R.id.imgFirstAnimal)
        val imgSecondAnimal = findViewById<ImageView>(R.id.imgSecondAnimal)

        val randomlyGeneratedAnimals = arrayOf(animal,animal2)

        wantedAnimal =  randomlyGeneratedAnimals.random()

        tvAnimal.text = wantedAnimal.name

        imgFirstAnimal.setImageResource(animal.image!!)
        imgFirstAnimal.contentDescription = animal.name

        imgSecondAnimal.setImageResource(animal2.image!!)
        imgSecondAnimal.contentDescription = animal2.name
    }

    fun imgViewClicked(view: View) {

        timer.cancel()

        val imgView = view as ImageView
        if(imgView.contentDescription == wantedAnimal.name){
            val intent = Intent(this, Level2::class.java)
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
            if(tts != null) {
                tts!!.language = Locale.US
                tts!!.speak("Find the ${wantedAnimal.name}. ${wantedAnimal.name} makes sound: ", TextToSpeech.QUEUE_FLUSH, null, "")
                mediaPlayer = MediaPlayer.create(this@Level1,wantedAnimal.sound!!)
                mediaPlayer!!.start()
            }

        }
    }
}