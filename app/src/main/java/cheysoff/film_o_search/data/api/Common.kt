package cheysoff.film_o_search.data.api

import cheysoff.film_o_search.data.api.retrofit.RetrofitClient
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices


object Common {
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/"
    private const val API_KEY = "99b69854-0eda-4dce-b56b-9199c73058a6"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL, API_KEY).create(RetrofitServices::class.java)

}