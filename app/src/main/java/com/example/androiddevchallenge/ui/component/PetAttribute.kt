package com.example.androiddevchallenge.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.greyLight

@Composable
fun PetAttribute(title: String, property: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = greyLight, shape = RoundedCornerShape(5.dp))
            .padding(5.dp)
            .defaultMinSize(minWidth = 80.dp)

    ) {

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = title,
            style = TextStyle(color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Light)
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = property,
            style = TextStyle(color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(10.dp))

    }


}