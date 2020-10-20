package com.example.designpatterns.console

//Decouple an abstraction from its implementation (PIMPL)

interface Device {
    fun getVolume(): Int
    fun setVolume(volume: Int)
}

class Tv : Device {
    private var volume = 0
    override fun getVolume(): Int {
        return volume
    }

    override fun setVolume(volume: Int) {
        this.volume = volume
        println("Set TV volume: $volume")
    }
}

class Radio : Device {
    private var volume = 0
    override fun getVolume(): Int {
        return volume
    }

    override fun setVolume(volume: Int) {
        this.volume = volume
        println("Set Radio volume: $volume")
    }
}

open class RemoteControl(protected val device: Device) {
    fun volumeUp() {
        device.setVolume(device.getVolume() + 1)
    }

    fun volumeDown() {
        device.setVolume(device.getVolume() - 1)
    }
}

class AdvancedRemoteControl(device: Device) : RemoteControl(device) {
    fun mute() {
        device.setVolume(0)
    }
}


fun main() {
    val tv = Tv()
    val remote = RemoteControl(tv)
    remote.volumeUp()

    val radio = Radio()
    val remote2 = AdvancedRemoteControl(radio)
    for(i in 0..3)
        remote2.volumeUp()
    remote2.mute()
}