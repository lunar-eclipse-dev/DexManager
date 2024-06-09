package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.ui.AutoSizeImage

private val typeColors = mapOf(
    "Bug" to Color(0xFF92BC2C),
    "Dark" to Color(0xFF595761),
    "Dragon" to Color(0xFF0C69C8),
    "Electric" to Color(0xFFF2D94E),
    "Fairy" to Color(0xFFEE90E6),
    "Fighting" to Color(0xFFD3425F),
    "Fire" to Color(0xFFFBA54C),
    "Flying" to Color(0xFFA1BBEC),
    "Ghost" to Color(0xFF5F6DBC),
    "Grass" to Color(0xFF5FBD58),
    "Ground" to Color(0xFFDA7C4D),
    "Ice" to Color(0xFF75D0C1),
    "Normal" to Color(0xFFA0A29F),
    "Poison" to Color(0xFFB763CF),
    "Psychic" to Color(0xFFFA8581),
    "Rock" to Color(0xFFC9BB8A),
    "Steel" to Color(0xFF5695A3),
    "Water" to Color(0xFF539DDF),
)

@Composable
fun TypeIcon(type: String) {
    Box(
        modifier = Modifier
            .height(32.dp)
            .aspectRatio(1f)
            .background(typeColors[type] ?: Color.Red, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        AutoSizeImage(
            "https://duiker101.github.io/pokemon-type-svg-icons/icons/${type.lowercase()}.svg",
            contentDescription = type,
            modifier = Modifier.fillMaxSize(0.6f)
        )
    }
}