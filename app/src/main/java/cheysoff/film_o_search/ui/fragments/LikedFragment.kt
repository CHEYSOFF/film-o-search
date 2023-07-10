package cheysoff.film_o_search.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.api.Common
import cheysoff.film_o_search.data.api.TopMoviesResponse
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices
import cheysoff.film_o_search.data.database.MovieViewModel
import cheysoff.film_o_search.data.models.MovieModel
import cheysoff.film_o_search.ui.MovieAdapter
import cheysoff.film_o_search.ui.MovieListController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import kotlin.coroutines.EmptyCoroutineContext
import cheysoff.film_o_search.MainActivity.Companion.viewModel



class LikedFragment(
    private val context: Context
) : Fragment(R.layout.fragment_liked) {
    private var moviesList: ArrayList<MovieModel> = arrayListOf()
    private lateinit var moviesLikedRecyclerView: RecyclerView

    private var adapter: MovieAdapter = MovieAdapter(moviesList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesList = arrayListOf()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_liked, container, false)

        moviesLikedRecyclerView = layout.findViewById(R.id.moviesLikedRecyclerView)
        moviesLikedRecyclerView.layoutManager = LinearLayoutManager(context)
        moviesLikedRecyclerView.adapter = adapter


        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                val tmp = viewModel.readAllData
                tmp.observe(viewLifecycleOwner, Observer { movie -> adapter.setData(movie) })

                Log.d(tmp.value.orEmpty().size.toString(), "yyy")
                moviesList.clear()
                if (tmp.value != null) {
                    for (movie in tmp.value!!) {
                        moviesList.add(movie)
                    }
                }
            }
        }


        return layout
    }

}