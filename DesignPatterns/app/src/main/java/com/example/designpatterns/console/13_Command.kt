package com.example.designpatterns.console

class Fan {
    fun startRotate() {
        println("Fan is rotating")
    }

    fun stopRotate() {
        println("Fan is not rotating")
    }
}

class Light {
    fun turnOn() {
        println("Light is on ")
    }

    fun turnOff() {
        println("Light is off")
    }
}

interface Command {
    fun execute()
}

class Switch(
    private val upCommand: Command,
    private val downCommand: Command
) {
    fun flipUp() {
        upCommand.execute()
    }

    fun flipDown() {
        downCommand.execute()
    }
}

class LightOnCommand(private val myLight: Light) : Command {
    override fun execute() {
        myLight.turnOn()
    }

}

class LightOffCommand(private val myLight: Light) : Command {
    override fun execute() {
        myLight.turnOff()
    }

}

class FanOnCommand(private val myFan: Fan) : Command {
    override fun execute() {
        myFan.startRotate()
    }

}

class FanOffCommand(private val myFan: Fan) : Command {
    override fun execute() {
        myFan.stopRotate()
    }

}


fun main() {

    val light = Light()
    val lightOnCommand = LightOnCommand(light)
    val lightOffCommand = LightOffCommand(light)

    val switch = Switch(lightOnCommand, lightOffCommand)
    switch.flipUp()
    switch.flipDown()

    val fan = Fan()
    val fanOnCommand = FanOnCommand(fan)
    val fanOffCommand = FanOffCommand(fan)

    val switch2 = Switch(fanOnCommand, fanOffCommand)
    switch2.flipUp()
    switch2.flipDown()

}