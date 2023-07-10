package cheysoff.film_o_search.data.database

import android.util.Log
import androidx.lifecycle.LiveData
import cheysoff.film_o_search.data.database.dao.MovieDao
import cheysoff.film_o_search.data.models.MovieModel

class MoviesRepository(private val movieDao: MovieDao) {
    var readAllData: LiveData<List<MovieModel>> = movieDao.getAll()

    fun addMovie(movie: MovieModel) {
        Log.d("3", "added")
        movieDao.insert(movie)
        Log.d("id:", movie.filmId.toString())
    }

    fun deleteMovie(id: Int) {
        movieDao.delete(id)
    }

    fun isLiked(id: Int): Boolean{
        return movieDao.isLiked(id)
    }
}