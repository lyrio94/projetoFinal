package projeto.iesb.br.projetofinal.repository


import com.google.firebase.auth.FirebaseAuth
import projeto.iesb.br.projetofinal.domain.ForgotPassword
import projeto.iesb.br.projetofinal.domain.LoginResult
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ForgotPasswordRepository {
    suspend fun resgatarSenha(data: ForgotPassword): LoginResult {
        return suspendCoroutine { resultadoPromisse ->

            val autenticacao = FirebaseAuth.getInstance();
            val operacao = autenticacao.sendPasswordResetEmail(data.email);
            operacao.addOnCompleteListener { resultado ->
                val resForgotPassword = LoginResult()
                if (resultado.isSuccessful) {
                    resForgotPassword.result = "CERTO: Senha enviada para usuario cadastrado"
                } else {
                    resForgotPassword.error = "Erro: Usuário inexistentes"
                }
                resultadoPromisse.resume(resForgotPassword)
            }
        }
    }
}