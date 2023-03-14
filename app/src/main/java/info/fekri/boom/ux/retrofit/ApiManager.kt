package info.fekri.boom.ux.retrofit

import info.fekri.boom.extra.BASE_URL_HEROES
import info.fekri.boom.ux.retrofit.models.HeroesData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL_HEROES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getHeroes(apiCallback: ApiCallback<ArrayList<HeroesData.HeroesDataItem>>) {
        apiService.getHeroes().enqueue(object : Callback<HeroesData> {
            override fun onResponse(
                call: Call<HeroesData>,
                response: Response<HeroesData>
            ) {
                val data = response.body()!!
                val dataToSend: ArrayList<HeroesData.HeroesDataItem> = arrayListOf()
                data.forEach { dataToSend.add(it) }

                // send data to fragment by apiCallback
                apiCallback.onSuccess(dataToSend)
            }

            override fun onFailure(call: Call<HeroesData>, t: Throwable) {
                apiCallback.onError(t.message!!)
            }
        })
    }

    interface ApiCallback<T> {

        fun onSuccess(data: T)
        fun onError(errMsg: String)

    }

}