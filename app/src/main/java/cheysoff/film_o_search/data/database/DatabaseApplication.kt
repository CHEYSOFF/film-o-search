package cheysoff.film_o_search.data.database

import android.app.Application

class DatabaseApplication: Application() {
    private val database by lazy { MovieDatabase.getDatabase(this) }
    val repository by lazy { MoviesRepository(database.movieDao()) }
}