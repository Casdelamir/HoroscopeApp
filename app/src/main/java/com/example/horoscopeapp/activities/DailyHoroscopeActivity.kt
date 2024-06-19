package com.example.horoscopeapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.horoscopeapp.data.Horoscope
import com.example.horoscopeapp.data.HoroscopeProvider
import com.example.horoscopeapp.R
import com.example.horoscopeapp.utilities.SessionManager

class DailyHoroscopeActivity : AppCompatActivity() {
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_horoscope)

        sessionManager = SessionManager(this)
        id = intent.getStringExtra(EXTRA_ID)!!

        favoriteImageButton = findViewById(R.id.favorite_icon_button)

        horoscope= HoroscopeProvider.getHoroscopeById(id)

        val imageView: ImageView = findViewById(R.id.daily_icon)
        val textView: TextView = findViewById(R.id.daily_name)

        imageView.setImageResource(horoscope.logo)
        textView.setText(horoscope.name)

        favorite = sessionManager.getFavoriteHoroscope().toString()

        favoriteImageButton.setOnClickListener {
            setFavorite()
        }

        supportActionBar?.setTitle(horoscope.name)
        supportActionBar?.setSubtitle(horoscope.description)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(horoscope.name))
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                Log.i("MENU", "Share is selected")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}