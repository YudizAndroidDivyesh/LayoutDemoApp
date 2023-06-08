package com.example.demooflayouts.koinDI

class Pilot(private val fly: Fly, private val eat: Eat) {

    fun perform(): String = fly.flyPlane() + "\n" + eat.eatFood()
}

class Eat {
    fun eatFood(): String = "I am eating food"
}

class Fly {
    fun flyPlane(): String = "I am flying a plane"
}