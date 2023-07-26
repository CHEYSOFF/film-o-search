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
import java.text.DateFormatSymbols
import java.util.Calendar

class TicketFragment : Fragment(R.layout.fragment_ticket) {

    private var moviesList: ArrayList<MovieModel>  = arrayListOf()
    private lateinit var moviesTicketRecyclerView: RecyclerView
    private val viewModel: MovieViewModel by activityViewModels()
    private lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_ticket, container, false)
        moviesTicketRecyclerView = layout.findViewById(R.id.moviesTicketRecyclerView)
        moviesTicketRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = MovieAdapter(moviesList, viewLifecycleOwner, viewModel)
        moviesTicketRecyclerView.adapter = adapter
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieListController =
            object : MovieListController(moviesTicketRecyclerView, viewLifecycleOwner) {
                override fun doRequest(mService: RetrofitServices): Call<TopMoviesResponse> {
                    val calendar = Calendar.getInstance()
                    val currentMonth =
                        DateFormatSymbols().months[calendar.get(Calendar.MONTH)].uppercase()
                    val currentYear = calendar.get(Calendar.YEAR)
                    Log.d(
                        "M + Y",
                        "$currentMonth $currentYear " + calendar.get(Calendar.MONTH).toString()
                    )
                    return mService.getPremiereMovies(currentYear, currentMonth)
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