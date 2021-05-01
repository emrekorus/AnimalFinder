package com.ybu.animalfinder.utils
import android.content.res.Resources
import com.ybu.animalfinder.R
import com.ybu.animalfinder.models.Animal

class Constants {
    companion object {
        fun getAnimals() : ArrayList<Animal> {

            val animals = ArrayList<Animal>()

            animals.add(Animal("Bat",R.drawable.bat,R.raw.animal_1))
            animals.add(Animal("Bear",R.drawable.bear,R.raw.animal_2))
            animals.add(Animal("Cat",R.drawable.cat,R.raw.animal_3))
            animals.add(Animal("Chicken",R.drawable.chicken,R.raw.animal_4))
            animals.add(Animal("Cock",R.drawable.cock,R.raw.animal_5))
            animals.add(Animal("Cow",R.drawable.cow,R.raw.animal_6))
            animals.add(Animal("Dog",R.drawable.dog,R.raw.animal_7))
            animals.add(Animal("Duck",R.drawable.duck,R.raw.animal_8))
            animals.add(Animal("Elephant",R.drawable.elephant,R.raw.animal_9))
            animals.add(Animal("Horse",R.drawable.horse,R.raw.animal_10))
            animals.add(Animal("Lion",R.drawable.lion,R.raw.animal_11))
            animals.add(Animal("Monkey",R.drawable.monkey,R.raw.animal_12))
            animals.add(Animal("Parrot",R.drawable.parrot,R.raw.animal_13))
            animals.add(Animal("Sheep",R.drawable.sheep,R.raw.animal_14))

            return animals
        }
    }
}