package cheysoff.film_o_search.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.database.MovieViewModel
import cheysoff.film_o_search.data.models.MovieModel

class MovieAdapter(
    private var moviesList: ArrayList<MovieModel>,
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: MovieViewModel
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(
            layoutInflater.inflate(
                R.layout.movie_bar,
                parent,
                false
            ),
            viewLifecycleOwner,
            viewModel
        )

    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        Log.d("movie catched", movie.filmId.toString())
        holder.onBind(movie)
    }

    fun setData(movies: List<MovieModel>) {
        this.moviesList = movies as ArrayList<MovieModel>
        notifyDataSetChanged()
    }

}