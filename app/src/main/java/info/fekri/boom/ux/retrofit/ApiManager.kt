package info.fekri.boom.ux.retrofit

import android.util.Log
import info.fekri.boom.extra.BASE_URL
import info.fekri.boom.ux.retrofit.models.BookNewsData
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
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getNews(apiCallback: ApiCallback<ArrayList<Pair<String, String>>>) {
        try {
            apiService.getNews().enqueue(object : Callback<BookNewsData> {
                override fun onResponse(call: Call<BookNewsData>, response: Response<BookNewsData>) {
                    val data = response.body()!!
                    val dataToSend: ArrayList<Pair<String, String>> = arrayListOf()

                    data.items.forEach {
                        try {
                            dataToSend.add(
                                Pair(
                                    it.volumeInfo.title,
                                    it.volumeInfo.infoLink
                                )
                            )
                        } catch (e: Exception) {
                            Log.v("boomLog", e.message.toString())
                        }

                    }

                    // send data by apiCallback
                    apiCallback.onSuccess(dataToSend)
                }

                override fun onFailure(call: Call<BookNewsData>, t: Throwable) {
                    val message = t.message!!
                    apiCallback.onError(message)
                }
            })

        } catch (e: Exception) {
            Log.v("boomLog", e.message.toString())
        }
    }

    interface ApiCallback<T> {

        fun onSuccess(data: T)
        fun onError(errMsg: String)

    }

}