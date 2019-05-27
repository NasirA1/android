package com.example.mnemonicabuddy

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    var cardRenderer: CardRenderer? = null
    val deck = Deck()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val content = findViewById(R.id.content) as FrameLayout
        cardRenderer = FullDeckRenderer2d(this, deck)
        deck.renderer = cardRenderer

        content.addView((cardRenderer as FullDeckRenderer2d).canvas)

        btnShuffle.setOnClickListener {
            deck.shuffle()
            deck.render()
        }

        btnBicycleNewDeckOrder.setOnClickListener {
            deck.setBicycleNewDeckOrder()
            deck.render()
        }

        btnSpanishNewDeckOrder.setOnClickListener {
            deck.setSpanishNewDeckOrder()
            deck.render()
        }

        btnStayStack.setOnClickListener {
            deck.setStayStack()
            deck.render()
        }

        btnFaro.setOnClickListener {
            deck.faroShuffle()
            deck.render()
        }

        btnMnemonica.setOnClickListener {
            deck.setMnemonicaStack()
            deck.render()
        }
    }
}

