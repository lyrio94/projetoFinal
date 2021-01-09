package projeto.iesb.br.projetofinal.view.component

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import projeto.iesb.br.projetofinal.R

@BindingAdapter("imageUrl")
fun loadImage(componente: ImageView, imageURL: String?) {
    Picasso.get()
        .load(imageURL)
        .placeholder(R.drawable.magic_card)
        .into(componente)
}