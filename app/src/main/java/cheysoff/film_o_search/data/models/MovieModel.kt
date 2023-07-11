package cheysoff.film_o_search.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import cheysoff.film_o_search.data.convertes.CountryModelConverter
import cheysoff.film_o_search.data.convertes.GenreModelConverter

@Entity(tableName = "movies")
data class MovieModel(
    @PrimaryKey(autoGenerate = true)
    var filmId: Int,
    @Ignore val kinopoiskId: Int,
    @ColumnInfo(name = "nameRu") val nameRu: String?,
    @ColumnInfo(name = "nameEn") var nameEn: String?,
    @ColumnInfo(name = "year") val year: String?,
    @TypeConverters(CountryModelConverter::class) val countries: List<CountryModel>,
    @TypeConverters(GenreModelConverter::class) val genres: List<GenreModel>,
    @ColumnInfo(name = "rating") var rating: String?,
    @ColumnInfo(name = "posterUrl") val posterUrl: String?,
    @ColumnInfo(name = "liked") var liked: Boolean
) {
    constructor(filmId: Int, nameRu: String?, nameEn: String?, year: String?, countries: List<CountryModel>, genres: List<GenreModel>, rating: String, posterUrl: String, liked: Boolean)
            : this(filmId, 0, nameRu, nameEn, year, countries, genres, rating, posterUrl, liked)
}