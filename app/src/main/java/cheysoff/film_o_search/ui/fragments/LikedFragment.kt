package cheysoff.film_o_search.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.database.MovieViewModel
import cheysoff.film_o_search.data.models.MovieModel
import cheysoff.film_o_search.ui.MovieAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LikedFragment : Fragment(R.layout.fragment_liked) {

    private val viewModel: MovieViewModel by activityViewModels()

    private var moviesList: ArrayList<MovieModel> = arrayListOf()
    private lateinit var moviesLikedRecyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_liked, container, false)

        moviesLikedRecyclerView = layout.findViewById(R.id.moviesLikedRecyclerView)
        moviesLikedRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = MovieAdapter(moviesList, viewLifecycleOwner, viewModel)
        moviesLikedRecyclerView.adapter = adapter

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        TODO: FIX SHIMMERS
//        activity?.runOnUiThread {
//            getView()?.visibility = View.GONE
//            MainActivity.shimmerContainer.startShimmer()
//        }
        lifecycleScope.launch {

            withContext(Dispatchers.Main) {

                val tmp = viewModel.readAllData
                tmp.observe(viewLifecycleOwner) { movie -> adapter.setData(movie) }

                Log.d(tmp.value.orEmpty().size.toString(), "yyy")
                moviesList.clear()
                if (tmp.value != null) {
                    for (movie in tmp.value!!) {
                        moviesList.add(movie)
                    }
                }
            }
//            activity?.runOnUiThread {
//                MainActivity.shimmerContainer.stopShimmer()
//                MainActivity.shimmerContainer.visibility = View.GONE
//                getView()?.visibility = View.VISIBLE
//            }

        }
    }

}