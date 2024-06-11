package com.example.horoscopeapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val textView: TextView
    val descTextView: TextView

    init {
        textView = view.findViewById(R.id.horoscope_title)
        descTextView = view.findViewById(R.id.horoscope_description)
    }
}