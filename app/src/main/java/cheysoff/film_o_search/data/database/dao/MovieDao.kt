package cheysoff.film_o_search.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cheysoff.film_o_search.data.models.MovieModel

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAll(): LiveData<List<MovieModel>>

    @Query("DELETE FROM movies WHERE filmId = :id")
    fun delete(id: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: MovieModel)

    @Query("SELECT EXISTS(SELECT * FROM movies WHERE filmId = :id)")
    fun isLiked(id: Int): Boolean
}
