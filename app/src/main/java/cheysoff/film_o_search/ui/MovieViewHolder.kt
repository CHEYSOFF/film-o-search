package cheysoff.film_o_search.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.Movie

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
    private val movieName: TextView = itemView.findViewById(R.id.movie_name)
    private val movieDescription: TextView = itemView.findViewById(R.id.movie_description)
    private val movieLike: ImageView = itemView.findViewById(R.id.movie_like)

    fun onBind(movie: Movie) {
        movieImage.setImageDrawable(movie.image)
        movieName.text = movie.name
        movieDescription.text = movie.description
        movieLike.setImageResource(R.drawable.like)
        movieLike.visibility = if (movie.liked) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}