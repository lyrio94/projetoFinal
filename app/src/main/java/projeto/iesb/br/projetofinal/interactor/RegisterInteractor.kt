package projeto.iesb.br.projetofinal.interactor

import projeto.iesb.br.projetofinal.domain.RegisterData
import projeto.iesb.br.projetofinal.domain.RegisterResult
import projeto.iesb.br.projetofinal.repository.RegisterRepository

class RegisterInteractor {
    var repo = RegisterRepository()


    suspend fun cadastrar(data: RegisterData): RegisterResult {

        val resultado = RegisterResult()

        if (data.email.isBlank() ){
            resultado.error = "ERRO_EMAIL_VAZIO"
            return resultado
        }
        if (data.pass.isBlank() ){
            resultado.error = "ERRO_SENHA_VAZIA"
            return resultado
        }
        if (data.pass.length < 6 ){
            resultado.error = "ERRO_SENHA_MENOR_6"
            return resultado
        }
        if (data.pass != data.confirmPass ){
            resultado.error = "ERRO_CONFIRMACAO_SENHA"
            return resultado
        }

        return repo.cadastrar(data)
    }
}