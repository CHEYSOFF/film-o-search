package cheysoff.film_o_search.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import cheysoff.film_o_search.data.convertes.CountryModelConverter
import cheysoff.film_o_search.data.convertes.GenreModelConverter

@Entity(tableName = "movies")
data class MovieModel(
    @PrimaryKey(autoGenerate = true) var filmId: Int,
    @Ignore val kinopoiskId: Int,
    val nameRu: String?,
    var nameEn: String?,
    val year: String?,
    @TypeConverters(CountryModelConverter::class) val countries: List<CountryModel>,
    @TypeConverters(GenreModelConverter::class) val genres: List<GenreModel>,
    var rating: String?,
    @Ignore var ratingKinopoisk: Float?,
    val posterUrl: String?,
    var liked: Boolean,
    @Ignore var description: String? = ""
) {
    constructor(
        filmId: Int,
        nameRu: String?,
        nameEn: String?,
        year: String?,
        countries: List<CountryModel>,
        genres: List<GenreModel>,
        rating: String,
        posterUrl: String,
        liked: Boolean
    ) : this(
        filmId, 0, nameRu, nameEn, year, countries, genres, rating, 0.0f, posterUrl, liked, ""
    )
}