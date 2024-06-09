package dev.lunar_eclipse.dexmanager.db

data class DexEntry(
    val id: Long,
    val name: String,
    val formName: String?,
    val formId: String?,
    val genderId: String?,
    val keyword: String,
    val generation: Long,
    val type1: String,
    val type2: String?,
    val national: Long,
    val swordShield: String?,
    val brilliantShining: String?,
    val legendsArceus: String?,
    val scarletViolet: String?
) {
    val normalSprite: String
        get() = getSpriteUrl("normal")
    val shinySprite: String
        get() = getSpriteUrl("shiny")

    fun getSpriteUrl(shiny: Boolean) = if (shiny) shinySprite else normalSprite

    val type1Sprite: String
        get() = "https://pokejungle.net/sprites/typeico/${type1.lowercase()}.png"
    val type2Sprite: String?
        get() = type2?.let { type2 -> "https://pokejungle.net/sprites/typeico/${type2.lowercase()}.png" }

    fun getSpriteUrl(type: String): String {
        val paddedId = national.toString().padStart(4, '0')
        val suffix = (formId ?: "") + (genderId ?: "")

        return "https://pokejungle.net/sprites/$type/$paddedId$suffix.png"
    }

    val formGender: String
        get() = (formName ?: "") + (genderId ?: "")
}