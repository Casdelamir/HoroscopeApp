package com.example.horoscopeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val horoscopeList: List<Horoscope>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horoscope_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return horoscopeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = horoscopeList[position]
        holder.textView.text = item.name
        holder.imageView.setImageResource(item.logo)
    }
}