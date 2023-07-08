package cheysoff.film_o_search.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.api.TopMoviesResponse
import cheysoff.film_o_search.data.api.models.MovieModel
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices
import cheysoff.film_o_search.ui.MovieListController
import retrofit2.Call

class TicketFragment(private val context: Context) : Fragment(R.layout.fragment_ticket) {

    private lateinit var moviesList: ArrayList<MovieModel>
    private lateinit var moviesTicketFragment: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesList = arrayListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_ticket, container, false)
        moviesTicketFragment = layout.findViewById(R.id.moviesTicketRecyclerView)

        val movieListController = object: MovieListController(moviesTicketFragment, context) {
            override fun doRequest(mService: RetrofitServices): Call<TopMoviesResponse> {
                // TODO: IMPLEMENT DATA CHANGE + SET CURRENT DATE BY DEFAULT
                return mService.getPremiereMovies(2023, "JUNE")
            }
        }
        movieListController.showFilms()

        return layout
    }

}