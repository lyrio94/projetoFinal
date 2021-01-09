package projeto.iesb.br.projetofinal.view.fragment

import AdaptadorCartas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import projeto.iesb.br.projetofinal.databinding.FragmentListaCartasBinding
import projeto.iesb.br.projetofinal.domain.MagicCard
import projeto.iesb.br.projetofinal.viewmodel.ApiViewModel


class ListaCartasFragment : Fragment() {

    private lateinit var binding: FragmentListaCartasBinding
    private val viewModel: ApiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaCartasBinding.inflate(inflater, container, false)
        binding.varFragment = this
        binding.lifecycleOwner = this

        viewModel.resultadoParaTela.observe(viewLifecycleOwner){ lista ->
            mostratResultadoAPI(lista)
        }


        binding.rvCartas.layoutManager = LinearLayoutManager(context)

        return binding.root
    }


    private fun mostratResultadoAPI(lista: List<MagicCard>){
        val adaptador = AdaptadorCartas(lista)

        binding.rvCartas.adapter = adaptador

    }

    fun chamarAPI() {
        viewModel.chamarAPI()
    }

}
