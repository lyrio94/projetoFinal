package projeto.iesb.br.projetofinal.repository.dto

data class MtgCardDTO(val id: String?,
                   val name: String,
                   val manaCost: String,
                   val colors: List<String>?,
                   val type: String,
                   val rarity: String,
                   val text: String?,
                   val power: String?,
                   val imageUrl: String?,
                   val layout: String,
                   val legalities: List<MtgCardLegalityDTO>?,
                   val foreignNames: List<MtgCardForeignNameDTO>?
                   )