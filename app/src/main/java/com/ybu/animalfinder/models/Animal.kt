package com.ybu.animalfinder.models

class Animal {
    var name: String? = null
    var image: Int? = null
    var sound: Int? = null

    constructor()

    constructor(name: String?, image: Int?, sound: Int?) {
        this.name = name
        this.image = image
        this.sound = sound
    }

    override fun toString(): String {
        return "Animal(name=$name, image=$image, sound=$sound)"
    }


}