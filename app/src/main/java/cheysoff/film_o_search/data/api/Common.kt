package cheysoff.film_o_search.data.api

import cheysoff.film_o_search.data.api.retrofit.RetrofitClient
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices


object Common {
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"
    private const val API_KEY = "07e9b390-9381-4885-b777-65bfceff7081"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL, API_KEY).create(RetrofitServices::class.java)

}