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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.EmptyCoroutineContext


class HomeFragment(private val context: Context) :
    Fragment(R.layout.fragment_home) {
    private lateinit var adapter: MovieAdapter
    private lateinit var moviesList: ArrayList<MovieModel>
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var mService: RetrofitServices


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesList = arrayListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val ll = inflater.inflate(R.layout.fragment_home, container, false)
        moviesRecyclerView = ll.findViewById(R.id.moviesRecyclerView)

        mService = Common.retrofitService
        adapter = MovieAdapter(moviesList)
        moviesRecyclerView.layoutManager = LinearLayoutManager(context)
        moviesRecyclerView.adapter = adapter

        val scope = CoroutineScope(EmptyCoroutineContext)
        scope.launch {

            val call = mService.getTopMovies()
            Log.d("Proceed call", "")
            call.enqueue(object : Callback<TopMoviesResponse> {
                override fun onResponse(
                    call: Call<TopMoviesResponse>,
                    response: Response<TopMoviesResponse>
                ) {
                    if (response.code() == 200) {
                        val moviesResponse = response.body()!!
                        Log.d("Has films", moviesResponse.films.size.toString())
                        moviesList.clear()
                        for (film in moviesResponse.films) {
                            film.nameEn?.let { Log.d("FLM!!!", it) }
                            moviesList.add(film)
                        }
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<TopMoviesResponse>, t: Throwable) {
                    Log.d("hell", "hell")
                }
            })
        }
        return ll
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            HomeFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}