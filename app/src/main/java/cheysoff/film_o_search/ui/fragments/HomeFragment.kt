package cheysoff.film_o_search.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.api.Common
import cheysoff.film_o_search.data.api.TopMoviesResponse
import cheysoff.film_o_search.data.api.models.MovieModel
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices
import cheysoff.film_o_search.ui.MovieAdapter
import cheysoff.film_o_search.ui.MovieListController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.EmptyCoroutineContext


class HomeFragment(private val context: Context) :
    Fragment(R.layout.fragment_home) {
    private lateinit var moviesList: ArrayList<MovieModel>
    private lateinit var moviesTopRecyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesList = arrayListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_home, container, false)
        moviesTopRecyclerView = layout.findViewById(R.id.moviesTopRecyclerView)

        val movieListController = object: MovieListController(moviesTopRecyclerView, context) {
            override fun doRequest(mService: RetrofitServices): Call<TopMoviesResponse> {
                return mService.getTopMovies()
            }
        }
        movieListController.showFilms()

        return layout
    }

}