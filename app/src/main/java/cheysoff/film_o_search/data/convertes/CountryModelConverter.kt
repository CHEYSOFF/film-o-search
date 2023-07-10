package cheysoff.film_o_search.data.convertes

import androidx.room.TypeConverter
import cheysoff.film_o_search.data.models.CountryModel

class CountryModelConverter {
    @TypeConverter
    fun fromCountryModel(countryModelList: List<CountryModel>): String {
        return countryModelList.joinToString(", ") { it.country }
    }

    @TypeConverter
    fun toCountryModel(countryModelString: String): List<CountryModel> {
        return countryModelString.split(", ").map { CountryModel(it) }
    }
}