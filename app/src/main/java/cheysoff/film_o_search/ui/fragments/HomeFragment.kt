package cheysoff.film_o_search.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.api.TopMoviesResponse
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices
import cheysoff.film_o_search.ui.MovieListController
import retrofit2.Call


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var moviesTopRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_home, container, false)
        moviesTopRecyclerView = layout.findViewById(R.id.moviesTopRecyclerView)



        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieListController =
            object : MovieListController(moviesTopRecyclerView, viewLifecycleOwner) {
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
        movieListController.showFilms()

    }

}