package projeto.iesb.br.projetofinal.repository

import com.google.firebase.auth.FirebaseAuth
import projeto.iesb.br.projetofinal.domain.LoginData
import projeto.iesb.br.projetofinal.domain.LoginResult
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginRepository {
    suspend fun login(data: LoginData): LoginResult {
        return suspendCoroutine { resultadoPromisse ->
            val auth = FirebaseAuth.getInstance()
            val operacao = auth.signInWithEmailAndPassword(data.email, data.pass)
            operacao.addOnCompleteListener { resultado ->
                val resLogin = LoginResult()
                if (resultado.isSuccessful) {
                    resLogin.result = "LOGIN_FIREBASE_SUCESSO"
                } else {
                    resLogin.error = "ERRO_LOGIN_FIREBASE"
                }

                resultadoPromisse.resume(resLogin)
            }
        }

    }
}