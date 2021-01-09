package io.magicthegathering.kotlinsdk.api

import io.magicthegathering.kotlinsdk.model.card.MtgCard
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

class MtgCardApiClient {

    private object Holder {
        val INSTANCE: MtgCardApi = ApiClientBuilder.instance.create(MtgCardApi::class.java)
    }

    companion object {
        private val instance: MtgCardApi by lazy { Holder.INSTANCE }

        fun getAllCards(pageSize: Int = 10, page: Int = 0): Response<List<MtgCard>> {
            return instance.getAllCards(pageSize, page).execute()
        }
    }

 interface MtgCardApi {
        @GET("cards")
        fun getAllCards(@Query("pageSize") pageSize: Int, @Query("page") page: Int): 
                Call<List<MtgCard>>


    }
}