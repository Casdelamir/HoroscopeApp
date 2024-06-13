package com.example.horoscopeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DailyHoroscope : AppCompatActivity() {
    companion object {
        //static constant coming from the intent
        const val EXTRA_ID = "HOROSCOPE_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_horoscope)

        //here we are getting the id assigned to the EXTRA_ID constant in the intent extra
        val id = intent.getStringExtra(EXTRA_ID)!!

        val horoscope: Horoscope = HoroscopeProvider.getHoroscopeById(id)

        val imageView: ImageView = findViewById(R.id.daily_icon)
        val textView: TextView = findViewById(R.id.daily_name)

        imageView.setImageResource(horoscope.logo)
        textView.setText(horoscope.name)
    }
}