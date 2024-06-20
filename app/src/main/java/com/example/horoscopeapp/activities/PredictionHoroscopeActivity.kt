package com.example.horoscopeapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.horoscopeapp.data.Horoscope
import com.example.horoscopeapp.data.HoroscopeProvider
import com.example.horoscopeapp.R
import com.example.horoscopeapp.utilities.SessionManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

class PredictionHoroscopeActivity : AppCompatActivity() {
    companion object {
        //static constant coming from the intent
        const val EXTRA_ID = "HOROSCOPE_ID"
    }

    private var itemFavoriteMenu: MenuItem? = null
    lateinit var sessionManager: SessionManager
    var isFavorite: Boolean = false
    //here we are getting the id assigned to the EXTRA_ID constant in the intent extra
    lateinit var id: String
    lateinit var favorite: String
    lateinit var favoriteImageButton: ImageButton
    lateinit var horoscope: Horoscope
    lateinit var dailyPredicton: TextView
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var predictionText: TextView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prediction_horoscope)

        sessionManager = SessionManager(this)
        id = intent.getStringExtra(EXTRA_ID)!!

        favoriteImageButton = findViewById(R.id.favorite_icon_button)
        dailyPredicton = findViewById(R.id.daily_prediction)
        bottomNavigationView = findViewById(R.id.navigation)
        predictionText = findViewById(R.id.prediction)
        progressBar = findViewById(R.id.progress_bar)

        horoscope= HoroscopeProvider.getHoroscopeById(id)

        val imageView: ImageView = findViewById(R.id.daily_icon)

        imageView.setImageResource(horoscope.logo)

        favorite = sessionManager.getFavoriteHoroscope().toString()

        favoriteImageButton.setOnClickListener {
            setFavorite()
        }

        supportActionBar?.setTitle(horoscope.name)
        supportActionBar?.setSubtitle(horoscope.description)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setOnSelectItemOfBottomNavigation()
        bottomNavigationView.selectedItemId = R.id.menu_daily
    }
    fun setFavoriteIcons(id: String, imageResourceIcon: Int) {
        itemFavoriteMenu?.setIcon(imageResourceIcon)
        sessionManager.setHoroscopeToFavorite(id)
        favoriteImageButton.setImageResource(imageResourceIcon)
        favorite = sessionManager.getFavoriteHoroscope().toString()
    }

    fun setFavorite() {
        if (favorite == id){
            setFavoriteIcons("", R.drawable.baseline_favorite_border_24)
        }else {
            setFavoriteIcons(id, R.drawable.baseline_favorite_24)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        itemFavoriteMenu = menu?.findItem(R.id.menu_favorite)
        //set icons: all icons are not favorite by default
        if (favorite == id) {
            itemFavoriteMenu?.setIcon(R.drawable.baseline_favorite_24)
            favoriteImageButton.setImageResource(R.drawable.baseline_favorite_24)

        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            //This is the arrow button in the action bar
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menu_favorite -> {
                setFavorite()
                Log.i("MENU", "Favorite is selected")
                true
            }
            R.id.menu_share -> {
//              Implements share with other apps
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, dailyPredicton.text)
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                Log.i("MENU", "Share is selected")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getHoroscopePrediction(date: String) {
        progressBar.visibility = View.VISIBLE
        // Llamada en hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            var con: HttpURLConnection? = null
            try {
                val url = URL("https://horoscope-app-api.vercel.app/api/v1/get-horoscope/${date}?sign=${horoscope.id}&day=TODAY")
                con = url.openConnection() as HttpURLConnection
                con.requestMethod = "GET"
                val responseCode = con.responseCode
                val response = con.inputStream.bufferedReader().use(BufferedReader::readText)
                println(response)

                val data = JSONObject(response).getJSONObject("data")
                    .getString("horoscope_data")
// Ejecutamos en el hilo principal, esa funccion compruebas
// si el hilo que se esta ejecutando es UI y si no ejecuta los comandos el la fuccion en el ilo UI
                runOnUiThread {
                    dailyPredicton.text = data
                    progressBar.visibility = View.GONE
                }
                Log.i("HTTP", "Response Code :: $responseCode")
            } catch (ex: Exception) {
                println(ex.printStackTrace())
            } finally {
                con?.disconnect()
            }
        }
    }

    fun setOnSelectItemOfBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener {menuItem ->
            when (menuItem.itemId) {
                R.id.menu_daily -> {
                    predictionText.text = getText(R.string.daily)
                    getHoroscopePrediction("daily")
                true
                }
                R.id.menu_weekly -> {
                    predictionText.text = getText(R.string.weekly)
                    getHoroscopePrediction("weekly")
                true
                }
                R.id.menu_monthly -> {
                    predictionText.text = getText(R.string.monthly)
                    getHoroscopePrediction("monthly")
                    true
                }
                else -> {false}
            }
        }
    }

}