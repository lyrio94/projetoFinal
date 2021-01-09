package br.iesb.meuprimeiroapp.interactor

import projeto.iesb.br.projetofinal.domain.MagicCard
import projeto.iesb.br.projetofinal.repository.ApiRepository

class ApiInteractor {

    private val repo = ApiRepository()

    suspend fun chamarAPI(): List<MagicCard>{
        return repo.chamarAPI()

    }
}