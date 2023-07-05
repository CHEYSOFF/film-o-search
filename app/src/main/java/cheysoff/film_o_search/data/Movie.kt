package cheysoff.film_o_search.data

import android.graphics.drawable.Drawable
import java.net.URL

class Movie(
    val id: Int,
    var name: String,
    var description: String,
    val imageURL: URL?,
    var liked: Boolean
){
    init {
        if (description.length > 35) {
            description = description.substring(0, 35) + "..."
        }
        if (name.length > 30) {
            name = name.substring(0, 30) + "..."
        }
    }
}