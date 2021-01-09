package projeto.iesb.br.projetofinal.repository

import projeto.iesb.br.projetofinal.domain.MagicCard
import projeto.iesb.br.projetofinal.repository.dto.ResultadoDTO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MtgCardApi {
    @GET("cards")
    suspend fun getAllCards(@Query("pageSize") pageSize: Int, @Query("page") page: Int)
            : ResultadoDTO

}
 interface MtgCardoutrasChamadasApi {

     @GET("cards")
     suspend fun getAllCardsBySetCodeObservable(
         @Query("set") setCode: String,
         @Query("pageSize") pageSize: Int,
         @Query("page") page: Int
     ): ResultadoDTO


     @GET("cards")
     suspend fun getCardsByName(
         @Query("name") name: String,
         @Query("pageSize") pageSize: Int,
         @Query("page") page: Int
     ): ResultadoDTO
 }
    class ApiRepository {
        private val conector: Retrofit

        init {
            conector = Retrofit.Builder()
                .baseUrl("https://api.magicthegathering.io/v1/cards/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        suspend fun chamarAPI(): List<MagicCard> {
            val service = conector.create(MtgCardApi::class.java)
            //https://api.magicthegathering.io/v1/cards?pageSize=2?page=15
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

