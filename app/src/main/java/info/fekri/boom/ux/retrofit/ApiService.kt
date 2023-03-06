package info.fekri.boom.ux.retrofit

import info.fekri.boom.extra.API_HOST
import info.fekri.boom.extra.API_KEY1
import info.fekri.boom.ux.retrofit.models.HeroesData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(API_KEY1, API_HOST)
    @GET("heroes")
    fun getHeroes(): Call<HeroesData>

}