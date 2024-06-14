package com.example.horoscopeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_favorite -> {
                Log.i("MENU", "Favorite is selected")
                true
            }
            R.id.menu_share -> {
                Log.i("MENU", "Share is selected")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}