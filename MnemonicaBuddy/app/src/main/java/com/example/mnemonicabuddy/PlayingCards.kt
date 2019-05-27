package com.example.mnemonicabuddy
import android.os.Build
import android.support.annotation.RequiresApi
import java.lang.Integer.max


enum class Suit {
    SPADES,
    DIAMONDS,
    CLUBS,
    HEARTS;

    fun index(): String {
        when(name) {
            "SPADES" -> return "♠"
            "DIAMONDS" -> return "♦"
            "CLUBS" -> return "♣"
            "HEARTS" -> return "♥"
            else -> return "?"
        }
    }
}

enum class Rank(val value: Int) {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);

    fun index(): String {
        val paddingLength = 2
        when(value) {
            1 -> return "A".padStart(paddingLength, ' ')
            in 2..10 -> return value.toString().padStart(paddingLength, ' ')
            11 -> return "J".padStart(paddingLength, ' ')
            12 -> return "Q".padStart(paddingLength, ' ')
            13 -> return "K".padStart(paddingLength, ' ')
            else -> return "?".padStart(paddingLength, ' ')
        }
    }
}


interface CardRenderer {
    fun render()
}


class Card(val rank: Rank, val suit: Suit)
{
    fun index(): String {
        return rank.index() + suit.index()
    }

    override fun toString(): String {
        return rank.toString() + " of " + suit
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is Card
            || !index().equals(other.index()))
            return false
        return true
    }

    override fun hashCode(): Int {
        return index().hashCode()
    }
}

class Deck {
    var cards = mutableListOf<Card>()
    var renderer: CardRenderer? = null

    constructor() {
        setBicycleNewDeckOrder()
    }

    fun shuffle() {
        cards.shuffle()
    }

    private fun addAllRanks(result: MutableList<Card>, suit: Suit, desc: Boolean = false) {
        if(desc)
            for (i in Rank.values().size - 1 downTo 0)
                result.add(Card(Rank.values()[i], suit))
        else
            for (rank in Rank.values())
                result.add(Card(rank, suit))
    }

    fun setBicycleNewDeckOrder() {
        var result = mutableListOf<Card>()
        addAllRanks(result, Suit.HEARTS)
        addAllRanks(result, Suit.CLUBS)
        addAllRanks(result, Suit.DIAMONDS, true)
        addAllRanks(result, Suit.SPADES, true)
        cards = result
    }

    fun setSpanishNewDeckOrder() {
        var result = mutableListOf<Card>()
        addAllRanks(result, Suit.SPADES)
        addAllRanks(result, Suit.HEARTS)
        addAllRanks(result, Suit.DIAMONDS, true)
        addAllRanks(result, Suit.CLUBS, true)
        cards = result
    }

    fun reverseCards(from: Int, to: Int) {
        var result = mutableListOf<Card>()
        var reversed = cards.subList(from, to).asReversed()

        if(from > 0)
            for(i in 0..from)
                result.add(cards[i])
        result.addAll(reversed)
        if(to < cards.size - 1)
            for(i in to..cards.size - 1)
                result.add(cards[i])

        cards = result
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun faroShuffle(topHalfCount: Int = 26) {
        var result = mutableListOf<Card>()

        var topHalf = cards.subList(0, topHalfCount)
        var bottomHalf = cards.subList(topHalfCount, cards.size)

        for(i in 0..max(topHalf.size, bottomHalf.size)) {
            if(i < topHalf.size) result.add(topHalf[i])
            if(i < bottomHalf.size) result.add(bottomHalf[i])
        }
        cards = result
    }

    fun render() {
        renderer!!.render()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setStayStack() {
        setSpanishNewDeckOrder()
        for(i in 0..3)
            faroShuffle()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setMnemonicaStack() {
        setStayStack()
        reverseCards(0, 26)
        faroShuffle(18)
        cut(9)
    }

    fun cut(cardCount: Int)
    {
        var result = mutableListOf<Card>()
        val topHalf = cards.subList(0, cardCount)
        val bottomHalf = cards.subList(cardCount, cards.size)
        result.addAll(bottomHalf)
        result.addAll(topHalf)
        cards = result
    }

    fun undercut(cardCount: Int)
    {
        var result = mutableListOf<Card>()
        val topHalf = cards.subList(cardCount, cards.size)
        val bottomHalf = cards.subList(0, cardCount)
        result.addAll(bottomHalf)
        result.addAll(topHalf)
        cards = result
    }

}


class TextCardRenderer(val cards:List<Card>): CardRenderer {

    override fun render() {
        println("Deck: ")
        var i = 0
        for(card in cards) {
            print(card.index() + " ")
            if(++i % 13 == 0)
                println()
        }
        println()
    }
}

class MnemonicaCardRenderer(val cards:List<Card>): CardRenderer {

    override fun render() {
        println("Mnemonica Stack: ")
        var i = 0
        for(card in cards)
            println(card.index() + " is " + ++i)
        println()
    }
}



@RequiresApi(Build.VERSION_CODES.N)
fun main() {
    val deck = Deck()
    deck.renderer = TextCardRenderer(deck.cards)
    deck.render()

    deck.setMnemonicaStack()
    deck.renderer = MnemonicaCardRenderer(deck.cards)
    deck.render()
}
