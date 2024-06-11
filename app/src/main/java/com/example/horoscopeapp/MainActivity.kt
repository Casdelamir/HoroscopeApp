package com.example.horoscopeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val horoscopeList: List<Horoscope> = listOf(
        Horoscope("aries", "Aries", 0),
        Horoscope("taurus", "Taurus", 0),
        Horoscope("gemini", "Gemini", 0),
        Horoscope("cancer", "Cancer", 0),
        Horoscope("leo", "Leo", 0),
        Horoscope("virgo", "Virgo", 0),
        Horoscope("libra", "Libra", 0),
        Horoscope("scorpio", "Scorpio", 0),
        Horoscope("sagittarius", "Sagittarius", 0),
        Horoscope("capricorn", "Capricorn", 0),
        Horoscope("aquarius", "Aquarius", 0),
        Horoscope("pisces", "Pisces", 0)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var recyclerView: RecyclerView

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val adapter = RecyclerAdapter(horoscopeList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

}