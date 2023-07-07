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
    val rating: String,
    val ratingVoteCount: Int,
    val posterUrl: String,
    val posterUrlPreview: String,
    val liked: Boolean
) {
    init {
        if (nameEn == null) {
            nameEn = ""
        }
//        if (description.length > 35) {
//            description = description.substring(0, 35) + "..."
//        }
        if (nameEn!!.length > 30) {
            nameEn = nameEn!!.substring(0, 30) + "..."
        }
    }
}