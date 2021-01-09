package projeto.iesb.br.projetofinal.repository

import com.google.firebase.auth.FirebaseAuth
import projeto.iesb.br.projetofinal.domain.RegisterData
import projeto.iesb.br.projetofinal.domain.RegisterResult
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RegisterRepository {
    suspend fun cadastrar(data: RegisterData): RegisterResult {
        return suspendCoroutine { resultadoPromisse ->
            val auth = FirebaseAuth.getInstance()
            val operacao = auth.createUserWithEmailAndPassword(data.email, data.pass)
            operacao.addOnCompleteListener{
                operacao.addOnCompleteListener() { resultado ->
                    val resRegister = RegisterResult()
                    if (resultado.isSuccessful) {
                        resRegister.result = "USUARIO_CRIADO"
                    }

                    resultadoPromisse.resume(resRegister)
                }
            }
        }

    }
}