package cheysoff.film_o_search.data.api.retrofit

import cheysoff.film_o_search.data.api.TopMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServices {

    @GET("v2.2/films/top")
    fun getTopMovies (
        @Query("type") type: String = "TOP_100_POPULAR_FILMS",
        @Query("page") page: Int = 1
    ) : Call<TopMoviesResponse>

    @GET("v2.2/films/premieres")
    fun getPremiereMovies (
        @Query("year") year: Int,
        @Query("month") page: String
    ) : Call<TopMoviesResponse>

    @GET("v2.2/films/{id}")
    fun getMovieData (
        @Path("id") id: Int
    ) : Call<TopMoviesResponse>
}