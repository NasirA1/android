package com.example.designpatterns.console

/*
The purpose of a Visitor pattern is to define a new operation without introducing the modifications
to an existing object structure.

Imagine that we have a composite object which consists of components. The object's structure is fixed –
we either can't change it, or we don't plan to add new types of elements to the structure.

Now, how could we add new functionality to our code without modification of existing classes?
 */


interface Element {
    fun accept(visitor: Visitor)
}

interface Visitor {
    fun visit(jsonElement: JsonElement)
    fun visit(xmlElement: XmlElement)
}

class Document : Element {
    private val elements = mutableListOf<Element>()

    fun addElement(element: Element) =
        elements.add(element)

    override fun accept(visitor: Visitor) {
        elements.forEach {
            it.accept(visitor)
        }
    }
}

class JsonElement : Element {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

class XmlElement : Element {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

class ElementVisitor : Visitor {
    override fun visit(xmlElement: XmlElement) {
        println(
            "processing an XML element with uuid: " + xmlElement.hashCode()
        )
    }

    override fun visit(jsonElement: JsonElement) {
        println(
            "processing a JSON element with uuid: " + jsonElement.hashCode()
        )
    }
}

fun main() {
    val elementVisitor = ElementVisitor()

    val document = Document()
    document.addElement(JsonElement())
    document.addElement(JsonElement())
    document.addElement(XmlElement())

    document.accept(elementVisitor)
}