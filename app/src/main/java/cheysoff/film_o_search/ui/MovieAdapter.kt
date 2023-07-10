package cheysoff.film_o_search.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.MainActivity.Companion.viewModel
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.database.MovieViewModel
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
        val movie = moviesList[position]
//        val tmp = viewModel.isLiked(movie.filmId)
//        binding.lifecycleOwner?.let {
//            tmp.observe(
//                it,
//                Observer { liked -> movie.liked = (liked == true) })
//        }
        holder.onBind(movie)
    }

    fun setData(movies: List<MovieModel>){
        this.moviesList = movies as ArrayList<MovieModel>
        notifyDataSetChanged()
    }

}