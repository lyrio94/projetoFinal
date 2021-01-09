import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import projeto.iesb.br.projetofinal.R
import projeto.iesb.br.projetofinal.databinding.CartaDeMagicBinding
import projeto.iesb.br.projetofinal.domain.MagicCard

class AdaptadorCartas(val lista: List<MagicCard>) :
    RecyclerView.Adapter<AdaptadorCartas.GuardadorDeDadosCarta>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuardadorDeDadosCarta {
        val instanciaDoXML = LayoutInflater.from(parent.context)
            .inflate(R.layout.carta_de_magic, parent, false)
        return GuardadorDeDadosCarta(instanciaDoXML)
    }

    override fun onBindViewHolder(holder: GuardadorDeDadosCarta, position: Int) {
        val binding = holder.binding
        val c = lista[position]
        binding.carta = c
        binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class GuardadorDeDadosCarta(v: View) : RecyclerView.ViewHolder(v) {
        val binding: CartaDeMagicBinding = CartaDeMagicBinding.bind(v)


    }
}