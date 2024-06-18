package com.example.horoscopeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopeapp.R
import com.example.horoscopeapp.data.Horoscope
import com.example.horoscopeapp.utilities.SessionManager

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

        val favorite: Boolean = SessionManager(holder.context).isFavorite(item.id)
        if (favorite) {
            holder.iconFavorite.visibility = View.VISIBLE
        }else {
            holder.iconFavorite.visibility = View.GONE
        }
    }

    fun updateDataSet(horoscopeList: List<Horoscope>) {
        this.horoscopeList = horoscopeList
        notifyDataSetChanged()
    }
}