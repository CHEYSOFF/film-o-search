package cheysoff.film_o_search.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import cheysoff.film_o_search.data.convertes.CountryModelConverter
import cheysoff.film_o_search.data.convertes.GenreModelConverter

@Entity(tableName = "movies")
data class MovieModel(
    @PrimaryKey(autoGenerate = true)
    val filmId: Int,
    @ColumnInfo(name = "nameRu") val nameRu: String?,
    @ColumnInfo(name = "nameEn") var nameEn: String?,
    @ColumnInfo(name = "year") val year: String?,
    @TypeConverters(CountryModelConverter::class) val countries: List<CountryModel>,
    @TypeConverters(GenreModelConverter::class) val genres: List<GenreModel>,
    @ColumnInfo(name = "rating") val rating: String = "0",
    @ColumnInfo(name = "posterUrl") val posterUrl: String,
    @ColumnInfo(name = "liked") val liked: Boolean
)