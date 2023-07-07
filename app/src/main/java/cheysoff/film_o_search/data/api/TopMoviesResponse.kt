package cheysoff.film_o_search.data.api

import cheysoff.film_o_search.data.api.models.MovieModel

data class TopMoviesResponse(
    val pagesCount: Int,
    val films: List<MovieModel>
)
