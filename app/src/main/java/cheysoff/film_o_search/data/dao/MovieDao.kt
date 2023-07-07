package cheysoff.film_o_search.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cheysoff.film_o_search.data.api.models.MovieModel

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<MovieModel>

    @Query("DELETE FROM movies WHERE filmId = :id")
    fun delete(id: Int)

    @Insert
    fun insert(movie: MovieModel)

    @Query("SELECT * FROM movies WHERE filmId = :id")
    fun isLiked(id: Int): Boolean
}
