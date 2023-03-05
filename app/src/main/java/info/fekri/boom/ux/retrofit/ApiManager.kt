package info.fekri.boom.ux.retrofit


import android.util.Log
import info.fekri.boom.extra.BASE_URL
import info.fekri.boom.ux.retrofit.models.BestBookKTData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    private val apiService: ApiService
    private lateinit var dataVolumeInfo: BestBookKTData.Item.VolumeInfo

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getBooksData(apiCallback: ApiCallback<List<BestBookKTData.Item.VolumeInfo>>) {

        try {

            apiService.getShowBooksData().enqueue(object : Callback<BestBookKTData> {

                override fun onResponse(
                    call: Call<BestBookKTData>,
                    response: Response<BestBookKTData>
                ) {

                    try {

                        val data = response.body()

                        if (data != null) {
                            data.items.forEach {
                                val volumeInfo = it.volumeInfo
                                dataVolumeInfo = volumeInfo
                            }

                            val dataToSend: List<BestBookKTData.Item.VolumeInfo> =
                                listOf(dataVolumeInfo)
                            apiCallback.onSuccess(dataToSend)
                        }

                    } catch (e: Exception) {
                        Log.v("boomLog", e.message.toString())
                    }

                }

                override fun onFailure(call: Call<BestBookKTData>, t: Throwable) {
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