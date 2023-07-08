package cheysoff.film_o_search.data.api.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieModel(
    @PrimaryKey
    val filmId: Int,
    val nameRu: String?,
    var nameEn: String?,
    val year: String?,
    val filmLength: String?,
    val countries: List<CountryModel>,
    val genres: List<GenreModel>,
    val rating: String = "0",
    val ratingVoteCount: Int = 0,
    val posterUrl: String,
    val posterUrlPreview: String,
    val liked: Boolean
)