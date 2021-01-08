package projeto.iesb.br.projetofinal.interactor

import projeto.iesb.br.projetofinal.domain.LoginData
import projeto.iesb.br.projetofinal.domain.LoginResult
import projeto.iesb.br.projetofinal.repository.LoginRepository

class LoginInteractor {

    var repo = LoginRepository()


    suspend fun login(data: LoginData): LoginResult {

        val resultado = LoginResult()

        if (data.email.isBlank()) {
            resultado.error = "ERRO_EMAIL_VAZIO"
            return resultado
        }
        if (data.pass.isBlank()) {
            resultado.error = "ERRO_SENHA_VAZIA"
            return resultado
        }
        if (data.pass.length < 6) {
            resultado.error = "ERRO_SENHA_MENOR_6"
            return resultado
        }
        //após cricitas passar dados para repository
        val resultadoRepo = repo.login(data)

        return resultadoRepo
    }
}