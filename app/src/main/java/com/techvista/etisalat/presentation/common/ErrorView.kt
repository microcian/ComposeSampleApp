package com.techvista.etisalat.presentation.common

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorView(
    error: String,
    modifier: Modifier
) {
    Text(
        text = error,
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}