package cheysoff.film_o_search.data.api.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var retrofit: Retrofit? = null

    private fun provideOkHttp(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("X-API-KEY", apiKey)
                    .header("accept", "application/json")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    fun getClient(baseUrl: String, apiKey: String): Retrofit {
        if (retrofit == null) {
            val okHttpClient = provideOkHttp(apiKey)
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit!!
    }
}