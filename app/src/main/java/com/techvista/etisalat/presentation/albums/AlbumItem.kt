package com.techvista.etisalat.presentation.albums

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techvista.etisalat.R

@Composable
fun AlbumItem(
    name: Int,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.padding(10.dp)) {
        Text(text = "ALBUM ID : $name",
            color = colorResource(id = R.color.black),
            fontStyle = FontStyle.Normal,
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(4))
                .fillMaxWidth()
                .clickable {
                    onClick.invoke()
                }
                .padding(15.dp)
        )
    }
}