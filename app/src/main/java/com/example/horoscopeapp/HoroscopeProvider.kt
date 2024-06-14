package com.example.horoscopeapp
//singleton class (have only one instance)
class HoroscopeProvider {
    //all variables and functions in companion object are static
    companion object {
        private val horoscopeList: List<Horoscope> = listOf(
            Horoscope(
                "aries",
                R.string.horoscope_name_aries,
                R.mipmap.ic_aries_foreground,
                R.string.horoscope_date_aries
            ),
            Horoscope(
                "taurus",
                R.string.horoscope_name_taurus,
                R.mipmap.ic_taurus_foreground,
                R.string.horoscope_date_taurus
            ),
            Horoscope(
                "gemini",
                R.string.horoscope_name_gemini,
                R.mipmap.ic_gemini_foreground,
                R.string.horoscope_date_gemini
            ),
            Horoscope(
                "cancer",
                R.string.horoscope_name_cancer,
                R.mipmap.ic_cancer_foreground,
                R.string.horoscope_date_cancer
            ),
            Horoscope(
                "leo",
                R.string.horoscope_name_leo,
                R.mipmap.ic_leo_foreground,
                R.string.horoscope_date_leo
            ),
            Horoscope(
                "virgo",
                R.string.horoscope_name_virgo,
                R.mipmap.ic_virgo_foreground,
                R.string.horoscope_date_virgo
            ),
            Horoscope(
                "libra",
                R.string.horoscope_name_libra,
                R.mipmap.ic_libra_foreground,
                R.string.horoscope_date_libra
            ),
            Horoscope(
                "scorpio",
                R.string.horoscope_name_scorpio,
                R.mipmap.ic_scorpio_foreground,
                R.string.horoscope_date_scorpio
            ),
            Horoscope(
                "sagittarius",
                R.string.horoscope_name_sagittarius,
                R.mipmap.ic_sagittarius_foreground,
                R.string.horoscope_date_sagittarius
            ),
            Horoscope(
                "capricorn",
                R.string.horoscope_name_capricorn,
                R.mipmap.ic_capricorn_foreground,
                R.string.horoscope_date_capricorn
            ),
            Horoscope(
                "aquarius",
                R.string.horoscope_name_aquarius,
                R.mipmap.ic_aquarius_foreground,
                R.string.horoscope_date_aquarius
            ),
            Horoscope(
                "pisces",
                R.string.horoscope_name_pisces,
                R.mipmap.ic_pisces_foreground,
                R.string.horoscope_date_pisces
            )
        )
        fun getHoroscopeList() : List<Horoscope> {
            return horoscopeList
        }

        fun getHoroscopeById(id: String): Horoscope {
            //"it" represent each object in the list (it is horoscope object)
            //it is the same like {h -> h.id == id}
            return horoscopeList.find { it.id == id }!!
            //!! means that the returned object is not null and will exist
        }
        fun getAllHoroscopesByIdStartingWithTheGivenInput (input: String): List<Horoscope> {
            return horoscopeList.filter { h -> h.id.startsWith(input, true) }
        }
    }
}