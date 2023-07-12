package cheysoff.film_o_search.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.data.api.Common
import cheysoff.film_o_search.data.api.TopMoviesResponse
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices
import cheysoff.film_o_search.data.models.CountryModel
import cheysoff.film_o_search.data.models.GenreModel
import cheysoff.film_o_search.data.models.MovieModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.EmptyCoroutineContext

abstract class MovieListController(
    moviesTopRecyclerView: RecyclerView,
    val context: Context,
    viewLifecycleOwner: LifecycleOwner
) {
    private var adapter: MovieAdapter
    private var moviesList: ArrayList<MovieModel> = arrayListOf()
    private var mService: RetrofitServices = Common.retrofitService


    init {
        adapter = MovieAdapter(moviesList, viewLifecycleOwner)
        moviesTopRecyclerView.layoutManager = LinearLayoutManager(context)
        moviesTopRecyclerView.adapter = adapter

    }

    open fun showFilms() {
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
                        Log.d("Has films", movies.size.toString())
                        moviesList.clear()
                        for (film in movies) {
                            film.nameEn?.let { Log.d("FLM!!!", it) }
                            Log.d("id", film.filmId.toString())
                            Log.d("kinopoiskId", film.kinopoiskId.toString())
                            moviesList.add(film)
                        }
                        Log.d("size", moviesList.size.toString())
                        // TODO: THINK OF BETTER WAY
                        adapter.notifyDataSetChanged()
                    }
                    else {
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