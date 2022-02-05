package com.techvista.etisalat.presentation.albums

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techvista.etisalat.R
import com.techvista.etisalat.domain.model.Photos

@Composable
fun AlbumItem(
    photos: Photos?,
    onClick: (Photos) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "${photos?.albumId}",
                color = colorResource(id = R.color.textWhite),
                fontStyle = FontStyle.Normal,
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(15.dp, 0.dp, 50.dp, 40.dp)
                    .clickable {
                        photos?.let {
                            onClick.invoke(it)
                        }
                    }
            )
        }
    }
}

private fun getScreenHeight(): Int {
    return 300
}