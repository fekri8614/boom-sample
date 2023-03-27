package info.fekri.boom.model.retrofit

import info.fekri.boom.extra.API_HOST_HEROES
import info.fekri.boom.extra.API_KEY_HEROES
import info.fekri.boom.model.data.HeroesData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(API_KEY_HEROES, API_HOST_HEROES)
    @GET("heroes")
    fun getHeroes(): Call<HeroesData>

}