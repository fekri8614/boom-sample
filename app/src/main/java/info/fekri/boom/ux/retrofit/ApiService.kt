package info.fekri.boom.ux.retrofit

import info.fekri.boom.extra.API_KEY
import info.fekri.boom.extra.BASE_URL
import info.fekri.boom.ux.retrofit.models.BookNewsData
import info.fekri.boom.ux.retrofit.models.BuyBooksToUseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(BASE_URL)
    @GET("volumes")
    fun getNews(
        @Query("q") query: String = "storybook+for+kids+and+teens",
        @Query("orderBy") orderBy: String = "newest"
    ): Call<BookNewsData>

    @Headers(API_KEY)
    @GET("volumes")
    fun getDataBooks(
        @Query("q") query: String = "popular+books+for+kids+and+teens",
        @Query("orderBy") orderBy: String = "newest"
    ): Call<BuyBooksToUseData>

}