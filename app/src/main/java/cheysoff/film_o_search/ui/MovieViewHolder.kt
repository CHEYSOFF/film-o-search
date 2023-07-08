package cheysoff.film_o_search.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.api.models.MovieModel
import com.bumptech.glide.Glide
import java.lang.StringBuilder

class MovieViewHolder(private val context: Context, itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
    private val movieName: TextView = itemView.findViewById(R.id.movie_name)
    private val movieGenre: TextView = itemView.findViewById(R.id.movie_genre)
    private val movieYear: TextView = itemView.findViewById(R.id.movie_year)
    private val movieLike: ImageView = itemView.findViewById(R.id.movie_like)

    private val maxName = 50
    private val maxGenre = 100
    private val maxYear = 4

    fun onBind(movie: MovieModel) {
//        TODO: CHANGE TO PICASSO
        Glide.with(context).load(movie.posterUrl).into(movieImage)
//        TODO: Check URL

        val allGenresBuilder = StringBuilder()
        for (genre in movie.genres) {
            allGenresBuilder.append("$genre, ")
        }

        val allGenres = allGenresBuilder.substring(0, allGenresBuilder.length - 2)

        var actualName = if (movie.nameEn.isNullOrEmpty()) {
            movie.nameRu
        } else {
            movie.nameEn
        }

        movieName.text = trim(actualName, maxName)
        movieGenre.text = trim(allGenres, maxGenre)
        movieYear.text = trim(movie.year, maxYear)
        movieLike.setImageResource(R.drawable.liked_selected)
        movieLike.visibility = if (movie.liked) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun trim(string: String?, amount: Int): String {
        if (string != null && string.length > amount) {
            return string.substring(0, amount) + "..."
        }
        return string.orEmpty()
    }
}