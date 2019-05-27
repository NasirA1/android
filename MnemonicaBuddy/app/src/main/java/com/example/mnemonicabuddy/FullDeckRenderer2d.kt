package com.example.mnemonicabuddy

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Log
import android.view.View


fun getCardResourceId(context:Context, card: Card):Int {
    val resourceName = card.toString().toLowerCase().replace(' ', '_')
    val resourceId = context.resources.getIdentifier(resourceName, "mipmap", context.packageName)
    return resourceId
}


class FullDeckRenderer2d: CardRenderer {
    val context:Context
    val deck:Deck
    val canvas:CardCanvas
    val cardFaces:MutableMap<String, Bitmap>

    constructor(context: Context, deck: Deck) {
        this.context = context
        this.deck = deck
        this.deck.renderer = this
        cardFaces = loadBitmaps(deck.cards, context)
        canvas = CardCanvas(this)
    }

    private fun loadBitmaps(cards: List<Card>, context : Context):MutableMap<String, Bitmap> {
        val cardFaces = mutableMapOf<String, Bitmap>()
        for(card in cards) {
            val bmp = BitmapFactory.decodeResource(context.resources, getCardResourceId(context, card))
            if (bmp != null) {
                cardFaces[card.index()] = Bitmap.createScaledBitmap(bmp, (bmp.width * 0.15).toInt(),
                    (bmp.height * 0.15).toInt(), true)
            } else {
                Log.e("MnemonicaBuddy", "Unable to load bitmap: $card")
            }
        }
        Log.d("MnemonicaBuddy", " cardface bitmaps loaded: ${cardFaces.values.count()}")
        return cardFaces
    }

    override fun render() {
        canvas.invalidate()
    }

    class CardCanvas(val fullDeckRenderer:FullDeckRenderer2d) : View(fullDeckRenderer.context) {

        override fun onDraw(canvas: Canvas) {
            canvas.drawRGB(152, 251, 152)
            var columnCount = 0
            var rowCount = 0
            val LEFT_MARGIN = 20.0f
            val TOP_MARGIN = 20.0f

            for(card in fullDeckRenderer.deck.cards) {
                if (fullDeckRenderer.cardFaces.containsKey(card.index())) {
                    canvas.drawBitmap(fullDeckRenderer.cardFaces[card.index()], LEFT_MARGIN + (columnCount * 70),
                        TOP_MARGIN + (rowCount * 300), null)
                    columnCount++
                    if(columnCount > 12) {
                        columnCount = 0
                        rowCount++
                    }

                } else {
                    Log.e("MnemonicaBuddy", "Unable to load bitmap: $card")
                }
            }
        }
    }
}