package com.example.sdutest.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

@Composable
fun NetworkImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
) {
    AsyncImage(
        model =  imageUrl.toString(),
        modifier = modifier,
        contentDescription = "",
    )
}

@Preview
@Composable
private fun NetworkImagePreview() {
    NetworkImage(
        imageUrl = "",
        placeholder = painterResource(id = Color(0xFFFFFFFF).toArgb())
    )
}
