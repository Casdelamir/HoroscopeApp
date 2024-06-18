package com.example.horoscopeapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopeapp.data.Horoscope
import com.example.horoscopeapp.data.HoroscopeProvider
import com.example.horoscopeapp.R
import com.example.horoscopeapp.adapters.RecyclerAdapter


class MainActivity : AppCompatActivity() {
    lateinit var adapter: RecyclerAdapter
    lateinit var horoscopeList: List<Horoscope>

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var recyclerView: RecyclerView
        horoscopeList = HoroscopeProvider.getHoroscopeList()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        adapter = RecyclerAdapter(horoscopeList) { position ->
            navigateToDailyHoroscope(horoscopeList[position])
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        adapter.updateDataSet(horoscopeList)
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
                    val horoscopeList =
                        HoroscopeProvider.getAllHoroscopesByIdStartingWithTheGivenInput(newText)
                    adapter.updateDataSet(horoscopeList)
                    true
                } else false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
    fun navigateToDailyHoroscope(horoscope: Horoscope) {
        val intent = Intent(this, DailyHoroscopeActivity::class.java)
        //here we are assigning to the static constant of DailyHoroscopeActivity
        //class the name on which the value of the horoscope id will be passed
        intent.putExtra(DailyHoroscopeActivity.EXTRA_ID, horoscope.id)
        startActivity(intent)
    }
}