package cheysoff.film_o_search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.models.MovieModel

class MovieAdapter(private var moviesList : ArrayList<MovieModel>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(
            parent.context,
            layoutInflater.inflate(
                R.layout.movie_bar,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(moviesList[position])
    }

    fun setData(movies: List<MovieModel>){
        this.moviesList = movies as ArrayList<MovieModel>
        notifyDataSetChanged()
    }

}