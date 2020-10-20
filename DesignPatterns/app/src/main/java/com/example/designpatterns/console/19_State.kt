package com.example.designpatterns.console


interface GameObjectState {
    fun update()
    fun draw()
}

class GameCharacter {
    private var state: GameObjectState? = null

    fun walk() {
        state = WalkingState()
    }

    fun attack() {
        state = AttackingState()
    }

    fun update() {
        state?.update()
    }

    fun draw() {
        state?.draw()
    }
}

class WalkingState : GameObjectState {
    override fun update() {
        println("Updating WalkingState")
    }

    override fun draw() {
        println("Drawing WalkingState")
    }
}

class AttackingState : GameObjectState {
    override fun update() {
        println("Updating AttackingState")
    }

    override fun draw() {
        println("Drawing AttackingState")
    }
}

class GameEngine {

    val gameObjects = mutableListOf<GameCharacter>()

    fun addGameObject(o: GameCharacter) {
        gameObjects.add(o)
    }

    fun runOneStep() {
        gameObjects.forEach {
            it.update()
            it.draw()
        }
    }
}


fun main() {
    val game = GameEngine()
    val character = GameCharacter()

    game.addGameObject(character)
    character.walk()
    game.runOneStep()
    character.attack()
    game.runOneStep()
}