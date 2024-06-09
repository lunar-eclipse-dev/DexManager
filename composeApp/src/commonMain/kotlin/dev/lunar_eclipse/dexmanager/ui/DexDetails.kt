package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.ui.AutoSizeImage
import dev.lunar_eclipse.dexmanager.DexViewModel
import dev.lunar_eclipse.dexmanager.koinViewModel

@Composable
fun DexDetails(openCollect: () -> Unit) {
    val viewModel = koinViewModel<DexViewModel>()
    val currentIndex by viewModel.currentIndexState

    if (currentIndex != null) {
        val dex by viewModel.dexState.collectAsState()
        val item = dex[currentIndex!!]

        Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 64.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(64.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceBright,
                            MaterialTheme.shapes.medium
                        )
                ) {
                    AutoSizeImage(
                        item.dexEntry.getSpriteUrl(item.userData?.shiny == true),
                        contentDescription = "sprite",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            item.dexEntry.name,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W400
                        )

                        val formGender = item.dexEntry.formGender
                        if (formGender.isNotBlank()) {
                            Text(
                                formGender,
                                fontSize = 16.sp,
                            )
                        }
                    }
                    InfoChip(
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Tag,
                                contentDescription = "Number",
                            )
                        },
                        label = { Text(item.dexEntry.national.toString()) },
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TypeIcon(item.dexEntry.type1)

                if (item.dexEntry.type2 != null) {
                    Spacer(Modifier.width(12.dp))

                    TypeIcon(item.dexEntry.type2!!)
                }
            }

            HorizontalDivider()

            Row(
                modifier = Modifier.fillMaxWidth().clickable { openCollect() },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    if (item.userData == null) {
                        Icons.Outlined.CheckBoxOutlineBlank
                    } else {
                        Icons.Outlined.CheckBox
                    },
                    modifier = Modifier.padding(12.dp),
                    contentDescription = "Checkbox"
                )
                Text(
                    if (item.userData == null) {
                        "Catch"
                    } else {
                        "Edit"
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}