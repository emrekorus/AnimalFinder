package com.ybu.animalfinder.utils
import com.ybu.animalfinder.R
import com.ybu.animalfinder.models.Animal

class Constants {
    companion object {

        const val LEVEL1_SCORE_SCALE = 1
        const val LEVEL2_SCORE_SCALE = 2
        const val LEVEL3_SCORE_SCALE = 3
        const val LEVEL4_SCORE_SCALE = 4
        const val LEVEL5_SCORE_SCALE = 5

        fun getAnimals() : ArrayList<Animal> {

            val animals = ArrayList<Animal>()

            animals.add(Animal("Bat",R.drawable.bat,R.raw.bat))
            animals.add(Animal("Bee",R.drawable.bee,R.raw.bee))
            animals.add(Animal("Camel",R.drawable.camel,R.raw.camel))
            animals.add(Animal("Cat",R.drawable.cat,R.raw.cat))
            animals.add(Animal("Chicken",R.drawable.chicken,R.raw.chicken))
            animals.add(Animal("Cow",R.drawable.cow,R.raw.cow))
            animals.add(Animal("Crow",R.drawable.crow,R.raw.crow))
            animals.add(Animal("Dinosaur",R.drawable.dinosaur,R.raw.dinosaur))
            animals.add(Animal("Dog",R.drawable.dog,R.raw.dog))
            animals.add(Animal("Dove",R.drawable.dove,R.raw.dove))
            animals.add(Animal("Duck",R.drawable.duck,R.raw.duck))
            animals.add(Animal("Elephant",R.drawable.elephant,R.raw.elephant))
            animals.add(Animal("Frog",R.drawable.frog,R.raw.frog))
            animals.add(Animal("Horse",R.drawable.horse,R.raw.horse))
            animals.add(Animal("Lion",R.drawable.lion,R.raw.lion))
            animals.add(Animal("Owl",R.drawable.owl,R.raw.owl))
            animals.add(Animal("Pig",R.drawable.pig,R.raw.pig))
            animals.add(Animal("Rooster",R.drawable.rooster,R.raw.rooster))
            animals.add(Animal("Sheep",R.drawable.sheep,R.raw.sheep))
            animals.add(Animal("Swan",R.drawable.swan,R.raw.swan))
            animals.add(Animal("Tiger",R.drawable.tiger,R.raw.tiger))
            animals.add(Animal("Turkey",R.drawable.turkey,R.raw.turkey))
            animals.add(Animal("Wolf",R.drawable.wolf,R.raw.wolf))

            return animals
        }
    }


}