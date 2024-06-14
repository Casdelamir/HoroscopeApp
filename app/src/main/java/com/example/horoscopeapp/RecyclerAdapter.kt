package com.example.horoscopeapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.content.DialogInterface.OnClickListener

class RecyclerAdapter(private var horoscopeList: List<Horoscope>, private val onClickListener: (Int) -> Unit): RecyclerView.Adapter<ViewHolder>() {
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
        holder.textView.setText(item.name)
        holder.imageView.setImageResource(item.logo)
        holder.descTextView.setText(item.description)
        holder.card.setOnClickListener {
            onClickListener(position)
        }
    }

    fun updateDataSet(horoscopeList: List<Horoscope>) {
        this.horoscopeList = horoscopeList
        notifyDataSetChanged()
    }
}