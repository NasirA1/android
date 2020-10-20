package com.example.designpatterns.console

import java.util.*

class Memento(val article: String)

class Originator {

    var article: String = ""
        get() = field
        set(value) {
            println("From Originator: Current version of Article\n$value\n")
            field = value
        }

    fun storeInMemento(): Memento {
        println("From Originator: Saving to Memento")
        return Memento(article)
    }

    fun restoreFromMemnto(memento: Memento): String {
        article = memento.article
        println("From Originator: Previous Article saved in Memento\n$article\n")
        return article
    }
}

class Caretaker {
    private val savedMementos = Stack<Memento>()

    fun addMemento(memento: Memento) {
        savedMementos.add(memento)
    }

    fun getLastMemento(): Memento = savedMementos.pop()
}


fun main() {

    val caretaker = Caretaker()
    val originator = Originator()

    originator.article = "here is some input"
    caretaker.addMemento(originator.storeInMemento())

    originator.article = "here is some input. Some more work..."


    //undo
    originator.restoreFromMemnto(caretaker.getLastMemento())
}