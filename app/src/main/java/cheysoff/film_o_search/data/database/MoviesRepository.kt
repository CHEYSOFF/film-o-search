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
    }

//    fun getAllMovies(): LiveData<List<MovieModel>> {
//        readAllData = movieDao.getAll()
//        val tm = readAllData.value
//        Log.d("wad", tm.orEmpty().size.toString())
//        return readAllData
//    }
}