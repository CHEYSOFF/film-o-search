package cheysoff.film_o_search.data

import android.graphics.drawable.Drawable
import java.net.URL

class Movie(
    val id: Int,
    val name: String,
    val description: String,
    val image: Drawable?,
    var liked: Boolean
)