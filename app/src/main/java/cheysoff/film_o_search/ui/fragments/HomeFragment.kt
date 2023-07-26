package cheysoff.film_o_search.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: MovieViewModel by activityViewModels()

    private var moviesList: ArrayList<MovieModel> = arrayListOf()
    private lateinit var moviesTopRecyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_home, container, false)

        moviesTopRecyclerView = layout.findViewById(R.id.moviesTopRecyclerView)
        moviesTopRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = MovieAdapter(moviesList, viewLifecycleOwner, viewModel)
        moviesTopRecyclerView.adapter = adapter

        return layout

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieListController =
            object : MovieListController() {
                override fun doRequest(mService: RetrofitServices): Call<TopMoviesResponse> {
                    //        TODO: FIX SHIMMERS

//                    activity?.runOnUiThread {
//                        getView()?.visibility = View.GONE
//                        shimmerContainer.startShimmer()
//                    }

//                    activity?.runOnUiThread {
//                        shimmerContainer.stopShimmer()
//                        shimmerContainer.visibility = View.GONE
//                        getView()?.visibility = View.VISIBLE
//                    }
                    return mService.getTopMovies()
                }
            }

        movieListController.showFilms { movies ->
            Log.d("Has films", movies.size.toString())
            moviesList.clear()
            moviesList.addAll(movies)
            Log.d("size", movies.size.toString())

            adapter.notifyDataSetChanged()
        }

    }

}