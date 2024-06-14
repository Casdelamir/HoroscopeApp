package com.example.horoscopeapp

import android.content.Intent
import android.media.RouteListingPreference.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var recyclerView: RecyclerView
        val horoscopeList = HoroscopeProvider.getHoroscopeList()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        adapter = RecyclerAdapter(horoscopeList) { position ->
            navigateToDailyHoroscope(horoscopeList[position])
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchViewItem = menu?.findItem(R.id.menu_search)
        val searchView = searchViewItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return if (newText != null) {
                    val horoscopeList = HoroscopeProvider.getAllHoroscopesByIdStartingWithTheGivenInput(newText)
                    adapter.updateDataSet(horoscopeList)
                    true
                } else false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
    fun navigateToDailyHoroscope(horoscope: Horoscope) {
        val intent = Intent(this, DailyHoroscope::class.java)
        //here we are assigning to the static constant of DailyHoroscope
        //class the name on which the value of the horoscope id will be passed
        intent.putExtra(DailyHoroscope.EXTRA_ID, horoscope.id)
        startActivity(intent)
    }
}