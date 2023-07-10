package cheysoff.film_o_search.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cheysoff.film_o_search.data.convertes.CountryModelConverter
import cheysoff.film_o_search.data.convertes.GenreModelConverter
import cheysoff.film_o_search.data.models.MovieModel
import cheysoff.film_o_search.data.database.dao.MovieDao

@Database(entities = [MovieModel::class], version = 1, exportSchema = false)
@TypeConverters(CountryModelConverter::class, GenreModelConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movies"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}