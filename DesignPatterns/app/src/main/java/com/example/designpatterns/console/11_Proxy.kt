package com.example.designpatterns.console

import android.provider.ContactsContract

// Useful for pre-processing, post-processing and overriding functionality
// also caching and security mechanisms

//e.g. control access to DELETE query in a database

interface DatabaseEngine {
    fun executeQuery(query: String)
}

class MyDatabaseEngine : DatabaseEngine {
    override fun executeQuery(query: String) {
        println("executing query: $query")
    }
}

data class User(val isAdmin: Boolean = false)

class MyDatabaseEngineProxy(
    var user: User,
    private val databaseEngine:DatabaseEngine = MyDatabaseEngine()
): DatabaseEngine {

    override fun executeQuery(query: String) {
        if(query.startsWith("DELETE") && !user.isAdmin) {
            println("Sorry, I cannot let you do that!")
            return
        }
        databaseEngine.executeQuery(query)
    }

}

fun main() {

    val normalUser = User()
    val databaseEngineProxy = MyDatabaseEngineProxy(normalUser)
    databaseEngineProxy.executeQuery("SELECT * FROM stock")
    databaseEngineProxy.executeQuery("DELETE FROM stock")

    val adminUser = User(isAdmin = true)
    databaseEngineProxy.user = adminUser
    databaseEngineProxy.executeQuery("DELETE FROM stock")
}