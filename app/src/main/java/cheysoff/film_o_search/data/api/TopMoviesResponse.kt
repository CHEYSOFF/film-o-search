package cheysoff.film_o_search.data.api

import cheysoff.film_o_search.data.models.MovieModel

data class TopMoviesResponse (
    val pagesCount: Int,
    val type: Int,
    val films: List<MovieModel>?,
    val items: List<MovieModel>?
)
