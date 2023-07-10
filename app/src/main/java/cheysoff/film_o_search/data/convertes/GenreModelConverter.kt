package cheysoff.film_o_search.data.convertes

import androidx.room.TypeConverter
import cheysoff.film_o_search.data.models.GenreModel

class GenreModelConverter {
    @TypeConverter
    fun fromGenreModel(genreModelList: List<GenreModel>): String {
        return genreModelList.joinToString(", ") { it.genre }
    }

    @TypeConverter
    fun toGenreModel(genreModelString: String): List<GenreModel> {
        return genreModelString.split(", ").map { GenreModel(it) }
    }
}