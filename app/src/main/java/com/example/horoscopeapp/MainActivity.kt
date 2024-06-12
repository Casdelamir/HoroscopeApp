package com.example.horoscopeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    companion object {
        val horoscopeList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var recyclerView: RecyclerView
        val horoscopeList = HoroscopeList()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val adapter = RecyclerAdapter(horoscopeList.horoscopeList) { position ->
            navigateToDailyHoroscope(horoscopeList.horoscopeList[position])
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
    fun navigateToDailyHoroscope(horoscope: Horoscope) {
        val intent: Intent = Intent(this, DailyHoroscope::class.java)
        intent.putExtra("ID", horoscope.id)
        startActivity(intent)
    }
}