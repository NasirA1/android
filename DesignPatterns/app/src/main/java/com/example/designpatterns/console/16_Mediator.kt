package com.example.designpatterns.console

import java.io.Closeable


abstract class Colleague(protected val mediator: Mediator) : Closeable {

    init {
        mediator.subscribe(this)
    }

    override fun close() {
        mediator.unsubscribe(this)
    }

    abstract fun notify(sender: Colleague, message: String)
}

interface Mediator {
    fun subscribe(observer: Colleague)
    fun unsubscribe(observer: Colleague)
    fun notifyAll(sender: Colleague, message: String)
}


class ConcreteMediator : Mediator {

    companion object {
        const val COLLEAGUE1_INTERESTING_STUFF_EVENT = "COLLEAGUE1_INTERESTING_STUFF_EVENT"
    }

    private val colleagues = mutableSetOf<Colleague>()

    override fun subscribe(observer: Colleague) {
        colleagues.add(observer)
    }

    override fun unsubscribe(observer: Colleague) {
        colleagues.remove(observer)
    }

    override fun notifyAll(sender: Colleague, message: String) {
        colleagues.forEach {
            if(it != sender)
                it.notify(sender, message)
        }
    }

}

class Colleague1(mediator: Mediator) : Colleague(mediator) {
    override fun notify(sender: Colleague, message: String) {
        println("Colleague1 received message $sender: $message")
    }

    fun doInterestingStuff() {
        println("Doing interesting stuff...")
        mediator.notifyAll(this, ConcreteMediator.COLLEAGUE1_INTERESTING_STUFF_EVENT)
    }
}

class Colleague2(mediator: Mediator) : Colleague(mediator) {
    override fun notify(sender: Colleague, message: String) {
        println("Colleague2 received message from $sender: $message")
    }
}

class Colleague3(mediator: Mediator) : Colleague(mediator) {
    override fun notify(sender: Colleague, message: String) {
        println("Colleague3 received message from $sender: $message")
    }
}


fun main() {

    val mediator = ConcreteMediator()
    val colleague1 = Colleague1(mediator)
    val colleague2 = Colleague2(mediator)
    val colleague3 = Colleague3(mediator)

    colleague1.doInterestingStuff()

    colleague1.close()
    colleague2.close()
    colleague3.close()
}