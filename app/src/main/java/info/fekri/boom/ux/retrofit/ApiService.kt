package info.fekri.boom.ux.retrofit

import info.fekri.boom.ux.retrofit.models.BestBookKTData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("volume")
    fun getShowBooksData(
        @Query("q") query: String = "best+books+for+kids+and+teens",
        @Query("orderBy") order_by: String = "newest"
    ): Call<BestBookKTData>

}