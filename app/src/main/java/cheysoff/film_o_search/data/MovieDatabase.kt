package cheysoff.film_o_search.data

import androidx.room.Database
import androidx.room.RoomDatabase
import cheysoff.film_o_search.data.api.models.MovieModel
import cheysoff.film_o_search.data.dao.MovieDao

@Database(entities = [MovieModel::class], version = 1)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
}