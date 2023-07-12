package cheysoff.film_o_search.ui

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.MainActivity.Companion.viewModel
import cheysoff.film_o_search.R
import cheysoff.film_o_search.data.api.Common
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices
import cheysoff.film_o_search.data.models.MovieModel
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    private val movieRating: TextView = itemView.findViewById(R.id.movie_rating)

    private var mService: RetrofitServices = Common.retrofitService

    private val maxName = 50
    private val maxGenre = 100
    private val maxYear = 4

    // TODO: OPEN MOVIE INFO ON CLICK
    fun onBind(movie: MovieModel) {
        setUpId(movie)
        setUpGenres(movie)
        setUpName(movie)
        setUpRating(movie)
        setUpLiked(movie)
        setUpYear(movie)
        setUpPoster(movie)
    }

    private fun setUpId(movie: MovieModel) {
        if (movie.filmId == 0) {
            movie.filmId = movie.kinopoiskId
            Log.d(movie.filmId.toString(), movie.kinopoiskId.toString())
        }
    }

    private fun setUpGenres(movie: MovieModel) {
        val allGenresBuilder = StringBuilder()
        for (genre in movie.genres) {
            allGenresBuilder.append("$genre, ")
        }
        // TODO: RENAME THEM TO ENGLISH
        val allGenres = allGenresBuilder.substring(0, allGenresBuilder.length - 2)
        movieGenre.text = trim(allGenres, maxGenre)
    }

    private fun setUpName(movie: MovieModel) {
        val actualName = if (movie.nameEn.isNullOrEmpty()) {
            movie.nameRu
        } else {
            movie.nameEn
        }
        Log.d("name", actualName.orEmpty())
        movieName.text = trim(actualName, maxName)
    }

    private fun setUpRating(movie: MovieModel) {
        movie.rating = movie.rating.orEmpty()

        if (movie.rating.isNullOrEmpty()) {
            val call = mService.getMovieData(movie.filmId)
            call.enqueue(object : Callback<MovieModel> {
                override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                    if (response.code() == 200) {
                        val movieResponse = response.body()!!
                        movie.rating = movieResponse.ratingKinopoisk.toString()
                        if (movie.rating == "null") {
                            movie.rating = defaultRating
                        }
                        changeRatingColorAndText(movie)
                    }
                }

                override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                    Log.d("Error while requesting film info", t.message.orEmpty())
                }

            })
        }
        changeRatingColorAndText(movie)

    }

    private fun setUpLiked(movie: MovieModel) {
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

    private fun setUpYear(movie: MovieModel) {
        movieYear.text = trim(movie.year, maxYear)
    }

    private fun setUpPoster(movie: MovieModel) {
        //        TODO: CHANGE TO PICASSO
        //        TODO: Check URL
        Glide.with(context).load(movie.posterUrl).into(movieImage)
    }

    private fun changeRatingColorAndText(movie: MovieModel) {
        movie.rating = movie.rating.orEmpty()
        if (movie.rating.orEmpty().endsWith("%") || movie.rating!!.isEmpty()) {
            movie.rating = defaultRating
        }
        movieRating.text = movie.rating
        movieRating.setTextColor(
            if (movie.rating == defaultRating) {
                ContextCompat.getColor(context, R.color.black)
            } else if (movie.rating!!.toFloat() < 6) {
                ContextCompat.getColor(context, R.color.red)
            } else if (movie.rating!!.toFloat() < 8) {
                ContextCompat.getColor(context, R.color.orange)
            } else {
                ContextCompat.getColor(context, R.color.green)
            }
        )
        Log.d("rat", movie.rating!!)
    }

    private fun trim(string: String?, amount: Int): String {
        if (string != null && string.length > amount) {
            return string.substring(0, amount) + "..."
        }
        return string.orEmpty()
    }

    companion object {
        const val defaultRating = "-"
    }

}

