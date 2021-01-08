package projeto.iesb.br.projetofinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import projeto.iesb.br.projetofinal.R
import projeto.iesb.br.projetofinal.domain.LoginData
import projeto.iesb.br.projetofinal.domain.LoginResult
import projeto.iesb.br.projetofinal.interactor.LoginInteractor
import kotlin.coroutines.CoroutineContext

class LoginViewModel (val app: Application) :  AndroidViewModel(app), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main
    val interactor = LoginInteractor()

    val resultadoParaTela = MutableLiveData<LoginResult>()
    fun login(data: LoginData) {

        launch {
            val resultado = interactor.login(data)
            if (resultado.error != null){
                if (resultado.error == "ERRO_EMAIL_VAZIO"){
                    resultado.error = app.getString(R.string.email_required)
                } else if (resultado.error == "ERRO_SENHA_VAZIA"){
                    resultado.error = app.getString(R.string.password_required)
                } else if (resultado.error == "ERRO_SENHA_MENOR_6"){
                    resultado.error = app.getString(R.string.password_6_char)
                } else if (resultado.error == "ERRO_LOGIN_FIREBASE"){
                    resultado.error = app.getString(R.string.login_error)
                }
            } else {
                resultado.error = null
                resultado.result = "Login feito com sucesso!"
            }
            resultadoParaTela.value = resultado
        }
    }

}