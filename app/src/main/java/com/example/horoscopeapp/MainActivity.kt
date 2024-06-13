package com.example.horoscopeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var recyclerView: RecyclerView
        val horoscopeList = HoroscopeProvider.getHoroscopeList()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val adapter = RecyclerAdapter(horoscopeList) { position ->
            navigateToDailyHoroscope(horoscopeList[position])
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_search -> {
                Log.i("MENU", "Search is selected")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun navigateToDailyHoroscope(horoscope: Horoscope) {
        val intent = Intent(this, DailyHoroscope::class.java)
        //here we are assigning to the static constant of DailyHoroscope
        //class the name on which the value of the horoscope id will be passed
        intent.putExtra(DailyHoroscope.EXTRA_ID, horoscope.id)
        startActivity(intent)
    }
}