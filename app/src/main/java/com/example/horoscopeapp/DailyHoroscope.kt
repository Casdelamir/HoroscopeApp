package com.example.horoscopeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DailyHoroscope : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val horoscopeList = HoroscopeList()
        lateinit var imageView: ImageView
        lateinit var textView: TextView

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_horoscope)

        val id: String? = intent.getStringExtra("ID")

        var horoscope: Horoscope? = horoscopeList.getHoroscopeById(id)
    }
}