package projeto.iesb.br.projetofinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import projeto.iesb.br.projetofinal.R
import projeto.iesb.br.projetofinal.domain.ForgotPassword
import projeto.iesb.br.projetofinal.domain.LoginData
import projeto.iesb.br.projetofinal.domain.LoginResult
import projeto.iesb.br.projetofinal.interactor.ForgotPasswordInteractor
import kotlin.coroutines.CoroutineContext

class ForgotPasswordViewModel (val app: Application) :  AndroidViewModel(app), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main
    private val interactor = ForgotPasswordInteractor()

    val resultadoParaTela = MutableLiveData<LoginResult>()
    fun resgatarSenha(data: ForgotPassword) {

        launch {
            val resultado = interactor.resgatarSenha(data)
            resultadoParaTela.value = resultado
        }
    }

}