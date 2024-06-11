package com.example.horoscopeapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val textView: TextView
    val descTextView: TextView
    val imageView: ImageView

    init {
        textView = view.findViewById(R.id.horoscope_title)
        descTextView = view.findViewById(R.id.horoscope_description)
        imageView = view.findViewById(R.id.icon)

    }
}