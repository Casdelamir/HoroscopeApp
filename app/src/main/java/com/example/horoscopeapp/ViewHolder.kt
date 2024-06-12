package com.example.horoscopeapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val textView: TextView
    val descTextView: TextView
    val imageView: ImageView
    val card: CardView

    init {
        card = view.findViewById(R.id.card_horoscope)
        textView = view.findViewById(R.id.horoscope_title)
        descTextView = view.findViewById(R.id.horoscope_description)
        imageView = view.findViewById(R.id.icon)

    }
}