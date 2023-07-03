package cheysoff.film_o_search.data

import java.net.URL

class Movie(
    val id: Int,
    val name: String,
    val description: String,
    val imageURL: URL,
    var liked: Boolean
)