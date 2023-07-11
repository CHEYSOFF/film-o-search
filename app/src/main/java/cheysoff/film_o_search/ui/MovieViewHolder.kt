package cheysoff.film_o_search.ui

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.MainActivity.Companion.viewModel
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.models.MovieModel
import com.bumptech.glide.Glide

class MovieViewHolder(
    private val context: Context,
    itemView: View,
    private val viewLifecycleOwner: LifecycleOwner
) :
    RecyclerView.ViewHolder(itemView) {
    private val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
    private val movieName: TextView = itemView.findViewById(R.id.movie_name)
    private val movieGenre: TextView = itemView.findViewById(R.id.movie_genre)
    private val movieYear: TextView = itemView.findViewById(R.id.movie_year)
    private val movieLike: ImageButton = itemView.findViewById(R.id.movie_like)

    private val maxName = 50
    private val maxGenre = 100
    private val maxYear = 4

    // TODO: OPEN MOVIE INFO ON CLICK
    fun onBind(movie: MovieModel) {
//        TODO: CHANGE TO PICASSO
        Glide.with(context).load(movie.posterUrl).into(movieImage)
//        TODO: Check URL

        if (movie.filmId == 0) {
            movie.filmId = movie.kinopoiskId
            Log.d(movie.filmId.toString(), movie.kinopoiskId.toString())
        }

        val allGenresBuilder = StringBuilder()
        for (genre in movie.genres) {
            allGenresBuilder.append("$genre, ")
        }
        // TODO: RENAME THEM TO ENGLISH
        val allGenres = allGenresBuilder.substring(0, allGenresBuilder.length - 2)

        var actualName = if (movie.nameEn.isNullOrEmpty()) {
            movie.nameRu
        } else {
            movie.nameEn
        }
        Log.d("name", actualName.orEmpty())

        movie.rating = movie.rating.orEmpty()
        val tmp = viewModel.isLiked(movie.filmId)
        tmp.observe(viewLifecycleOwner) { liked ->
            movie.liked = (liked == true)
            movieLike.setImageResource(
                if (movie.liked) {
                    R.drawable.liked_selected
                } else {
                    R.drawable.liked_ghost
                }
            )
        }


        movieName.text = trim(actualName, maxName)
        movieGenre.text = trim(allGenres, maxGenre)
        movieYear.text = trim(movie.year, maxYear)

        movieLike.setOnClickListener {
            movie.liked = !movie.liked
            if (movie.liked) {
                movieLike.setImageResource(R.drawable.liked_selected)
                viewModel.addMovie(movie)
            } else {
                movieLike.setImageResource(R.drawable.liked_ghost)
                viewModel.deleteMovie(movie.filmId)
            }
        }

    }

    fun trim(string: String?, amount: Int): String {
        if (string != null && string.length > amount) {
            return string.substring(0, amount) + "..."
        }
        return string.orEmpty()
    }
}