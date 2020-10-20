package com.example.designpatterns.console

interface Disk : Cloneable {
    fun makeCopy(): Disk
}

class Cd : Disk {
    override fun makeCopy(): Disk {
        println("CD is being made")
        val cd= super.clone() as Cd
        return cd
    }

    override fun toString(): String {
        return "The CD hey!"
    }
}

class DiskCloneFactory {

    fun cloneDisk(disk: Disk): Disk = disk.makeCopy()

}


fun main() {
    val cd = Cd()
    val cloneFactory = DiskCloneFactory()
    val cpy = cloneFactory.cloneDisk(cd) as Cd

    println("cd = $cd")
    println("cd copy = $cpy")
}
