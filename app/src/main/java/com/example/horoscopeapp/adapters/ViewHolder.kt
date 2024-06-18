package com.example.horoscopeapp.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopeapp.R

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val textView: TextView
    val descTextView: TextView
    val imageView: ImageView
    val iconFavorite: ImageView
    val card: CardView
    val context: Context

    init {
        card = view.findViewById(R.id.card_horoscope)
        textView = view.findViewById(R.id.horoscope_title)
        descTextView = view.findViewById(R.id.horoscope_description)
        imageView = view.findViewById(R.id.icon)
        iconFavorite = view.findViewById(R.id.favoriteImageView)
        context = view.context
    }
}