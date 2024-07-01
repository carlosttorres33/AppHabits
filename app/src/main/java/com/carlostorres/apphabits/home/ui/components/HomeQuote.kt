package com.carlostorres.apphabits.home.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeQuote(
    quote: String,
    author: String,
    @DrawableRes image: Int,
    modifier : Modifier = Modifier
){

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .height(146.dp)
            .background(Color.White)
    ){

        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .graphicsLayer (scaleX = 2.5f, scaleY = 2.5f)
                .offset(x = -7.dp, y = 27.dp)
        )

        Column(
            modifier = Modifier
                .padding(vertical = 26.dp, horizontal = 16.dp)
                .align(alignment = Alignment.TopStart)
                .padding(end = 87.dp)
        ) {

            Text(
                text = quote.uppercase(),
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "- ${author.uppercase()}",
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

        }

    }

}