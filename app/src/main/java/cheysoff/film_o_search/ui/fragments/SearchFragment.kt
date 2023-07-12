package cheysoff.film_o_search.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.api.TopMoviesResponse
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices
import cheysoff.film_o_search.ui.MovieListController
import retrofit2.Call

class SearchFragment(
    private val context: Context,
) : Fragment(R.layout.fragment_search) {
    private var keyword = MutableLiveData<String?>()

    private lateinit var moviesSearchRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_search, container, false)
        moviesSearchRecyclerView = layout.findViewById(R.id.b)
//        moviesSearchRecyclerView.layoutManager = LinearLayoutManager(context)

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieListController =
            object : MovieListController(moviesSearchRecyclerView, context, viewLifecycleOwner) {
                override fun doRequest(mService: RetrofitServices): Call<TopMoviesResponse> {
                    Log.d("query", keyword.toString())
                    return mService.getMoviesByKeyword(keyword.toString())
                }
            }
        movieListController.showFilms()
        keyword.observe(viewLifecycleOwner) {
            Log.d("owner", viewLifecycleOwner.toString())
            val movieListController =
                object : MovieListController(moviesSearchRecyclerView, context, viewLifecycleOwner) {
                    override fun doRequest(mService: RetrofitServices): Call<TopMoviesResponse> {
                        Log.d("query", keyword.value.orEmpty())
                        return mService.getMoviesByKeyword(keyword.value.orEmpty())
                    }
                }
            movieListController.showFilms()
        }


    }

    fun setKeyword(query: String?) {
        this.keyword.value = query.orEmpty()
    }

}