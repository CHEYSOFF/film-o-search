package cheysoff.film_o_search.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.api.models.MovieModel
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices

class LikedFragment(private val context: Context) : Fragment(R.layout.fragment_liked) {
    private lateinit var moviesList: ArrayList<MovieModel>
    private lateinit var moviesLikedRecyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesList = arrayListOf()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_home, container, false)
        moviesLikedRecyclerView = layout.findViewById(R.id.moviesTopRecyclerView)
        // TODO: do database lol
        return layout
    }

}