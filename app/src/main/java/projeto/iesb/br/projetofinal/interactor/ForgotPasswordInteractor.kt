package projeto.iesb.br.projetofinal.interactor


import projeto.iesb.br.projetofinal.domain.ForgotPassword
import projeto.iesb.br.projetofinal.domain.LoginResult
import projeto.iesb.br.projetofinal.repository.ForgotPasswordRepository

class ForgotPasswordInteractor {
    var repo = ForgotPasswordRepository()

    suspend fun resgatarSenha(data: ForgotPassword): LoginResult {

        return repo.resgatarSenha(data)
    }
}