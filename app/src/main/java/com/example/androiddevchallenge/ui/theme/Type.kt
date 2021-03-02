/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R


// Set of Material typography styles to start with


val Lato = FontFamily(
    Font(R.font.lato_bold, FontWeight.W600),
    Font(R.font.lato_regular, FontWeight.W500),
    Font(R.font.lato_light, FontWeight.W300),
)
val typography = Typography(
    button = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    h2 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    h3 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.W500,
        fontSize = 18.sp
    ),
    h1 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),
    body1 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = .25.sp
    ),

    subtitle2 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp

    ),

    )
