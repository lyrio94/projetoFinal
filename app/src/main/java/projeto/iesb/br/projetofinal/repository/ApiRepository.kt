package projeto.iesb.br.projetofinal.repository

import com.google.gson.GsonBuilder
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import io.reactivex.Observable
import projeto.iesb.br.projetofinal.domain.MagicCard
import projeto.iesb.br.projetofinal.repository.dto.ResultadoDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private interface MtgCardApi {
    //https://api.magicthegathering.io/v1/cards?pageSize=2?page=15
    @GET("cards")
   suspend fun getAllCards(@Query("pageSize") pageSize: Int, @Query("page") page: Int)
   : ResultadoDTO

}

private interface MtgCardoutrasApi {
    @GET("cards")
    suspend fun getAllCardsObservable(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Observable<List<MtgCard>>

    @GET("cards")
    suspend fun getAllCardsBySetCodeObservable(
        @Query("set") setCode: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Observable<List<MtgCard>>


    @GET("cards")
    suspend fun getCardsByName(
        @Query("name") name: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Call<List<MtgCard>>

    class ApiRepository {
        private val conector: Retrofit

        init {

            val gsonConfig = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()
            conector = Retrofit.Builder()
                .baseUrl("https://api.magicthegathering.io/v1/cards/")
                .addConverterFactory(GsonConverterFactory.create(gsonConfig))
                .build()

        }

        suspend fun chamarAPI(): List<MagicCard> {
            val service = conector.create(MtgCardApi::class.java)
            val listadeCartas = service.getAllCards(2, 15).results

            return listadeCartas.map { dto ->
                MagicCard(
                    nome = dto.name,
                    custodeMana = dto.manaCost,
                    texto = dto.type,
                    poder = dto.power,
                    imagem = dto.imageUrl
                )
            }
        }

    }
}

