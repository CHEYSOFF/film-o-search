package cheysoff.film_o_search.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.api.TopMoviesResponse
import cheysoff.film_o_search.data.api.models.MovieModel
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices
import cheysoff.film_o_search.ui.MovieListController
import retrofit2.Call


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