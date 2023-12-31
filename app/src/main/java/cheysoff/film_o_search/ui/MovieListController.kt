package cheysoff.film_o_search.ui

import android.util.Log
import cheysoff.film_o_search.data.api.Common
import cheysoff.film_o_search.data.api.TopMoviesResponse
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices
import cheysoff.film_o_search.data.models.MovieModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.EmptyCoroutineContext

abstract class MovieListController {
    private var mService: RetrofitServices = Common.retrofitService


    open fun showFilms(callback: (films: ArrayList<MovieModel>) -> Unit) {
        val scope = CoroutineScope(EmptyCoroutineContext)
        scope.launch {

            val call = doRequest(mService)
            Log.d("Proceed call", "")
            call.enqueue(object : Callback<TopMoviesResponse> {
                override fun onResponse(
                    call: Call<TopMoviesResponse>,
                    response: Response<TopMoviesResponse>
                ) {
                    if (response.code() == 200) {
                        val moviesResponse = response.body()!!
//                        TODO: make it exception free
                        val movies = (moviesResponse.items ?: moviesResponse.films)!!
                        Log.d("success", movies.size.toString())
                        callback.invoke(movies as ArrayList<MovieModel>)
                    } else {
                        Log.d("error", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<TopMoviesResponse>, t: Throwable) {
                    Log.d("Error while requesting list of films", t.message.orEmpty())
                }
            })
        }
    }

    abstract fun doRequest(mService: RetrofitServices): Call<TopMoviesResponse>

}