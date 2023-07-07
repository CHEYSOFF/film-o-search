package cheysoff.film_o_search

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.data.api.Common
import cheysoff.film_o_search.data.api.TopMoviesResponse
import cheysoff.film_o_search.data.api.models.MovieModel
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices
import cheysoff.film_o_search.ui.MovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MovieAdapter
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesList: ArrayList<MovieModel>
    private lateinit var searchBar: SearchView

    private lateinit var mService: RetrofitServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        moviesList = arrayListOf()

        moviesRecyclerView = findViewById(R.id.moviesRecyclerView)
        mService = Common.retrofitService
        adapter = MovieAdapter(this, moviesList)
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView.adapter = adapter

        val scope = CoroutineScope(EmptyCoroutineContext)
        scope.launch {
            val call = mService.getTopMovies()
            Log.d("Proceed call", "")
            call.enqueue(object : Callback<TopMoviesResponse> {
                override fun onResponse(
                    call: Call<TopMoviesResponse>,
                    response: Response<TopMoviesResponse>
                ) {
                    if (response.code() == 200) {
                        val moviesResponse = response.body()!!
                        Log.d("Has films", moviesResponse.films.size.toString())
                        moviesList.clear()
                        for (film in moviesResponse.films) {
                            film.nameEn?.let { Log.d("FLM!!!", it) }
                            moviesList.add(film)
                        }
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<TopMoviesResponse>, t: Throwable) {
                    Log.d("hell", "hell")
                }
            })
        }

        searchBar = findViewById(R.id.search_bar_id)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

}