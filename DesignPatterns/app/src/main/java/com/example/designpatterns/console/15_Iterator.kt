package com.example.designpatterns.console

data class Book(var title: String, var author: String, var year: Int)

class Library {

    private val books = mutableListOf<Book>()

    fun addBook(book: Book) = books.add(book)

    operator fun iterator() = books.iterator()
}


fun main() {

    val library = Library()

    library.addBook(Book("Game Programming Patterns", "Robert Nystorm", 2015))
    library.addBook(Book("Effective Modern C++", "Scott Meyers", 2010))
    library.addBook(Book("Lighting Control", "Simpson", 1982))

    for(book in library) {
        println(book)
    }

}