package cheysoff.film_o_search.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.api.TopMoviesResponse
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices
import cheysoff.film_o_search.data.database.MovieViewModel
import cheysoff.film_o_search.data.models.MovieModel
import cheysoff.film_o_search.ui.MovieAdapter
import cheysoff.film_o_search.ui.MovieListController
import retrofit2.Call

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: MovieViewModel by activityViewModels()

    private var keyword = MutableLiveData<String?>()

    private var moviesList: ArrayList<MovieModel> = arrayListOf()
    private lateinit var moviesSearchRecyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_search, container, false)

        moviesSearchRecyclerView = layout.findViewById(R.id.b)
        moviesSearchRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = MovieAdapter(moviesList, viewLifecycleOwner, viewModel)
        moviesSearchRecyclerView.adapter = adapter

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieListController =
            object : MovieListController() {
                override fun doRequest(mService: RetrofitServices): Call<TopMoviesResponse> {
                    Log.d("query", keyword.value.orEmpty())
                    return mService.getMoviesByKeyword(keyword.value.orEmpty())
                }
            }

        keyword.observe(viewLifecycleOwner) {
            Log.d("owner", viewLifecycleOwner.toString())
            movieListController.showFilms { movies ->
                Log.d("Has films", movies.size.toString())
                moviesList.clear()
                moviesList.addAll(movies)
                Log.d("size", movies.size.toString())

                adapter.notifyDataSetChanged()
            }
        }

    }

    fun setKeyword(query: String?) {
        Log.d("q2", query.orEmpty())
        this.keyword.value = query.orEmpty()

    }

}