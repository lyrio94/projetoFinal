package projeto.iesb.br.projetofinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.iesb.meuprimeiroapp.interactor.ApiInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import projeto.iesb.br.projetofinal.domain.MagicCard
import kotlin.coroutines.CoroutineContext

class ApiViewModel(val app: Application): AndroidViewModel(app), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private val interactor = ApiInteractor()

    val resultadoParaTela = MutableLiveData<List<MagicCard>>()
    fun chamarAPI(){

        launch {
            val listaPresonagens = interactor.chamarAPI()
            resultadoParaTela.value = listaPresonagens
        }
    }

}