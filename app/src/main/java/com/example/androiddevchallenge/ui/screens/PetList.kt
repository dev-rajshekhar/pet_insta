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

package com.example.androiddevchallenge.ui.screens

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.AnimalCategory
import com.example.androiddevchallenge.data.PetModel
import com.example.androiddevchallenge.data.getAllAnimalCategory
import com.example.androiddevchallenge.data.getAllAnimalIcon
import com.example.androiddevchallenge.ui.component.PetCardItem

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Arrangement


@Composable
fun PetList(
    navigateToDetailsScreen: (PetModel) -> Unit,
    selectedCategory: AnimalCategory?,
    petList: List<PetModel>,
    onSelectedChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .fillMaxHeight()
    ) {
        CustomHeader()
        PetFilter(selectedCategory, onSelectedChanged)
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            content = {
                items(petList.size) { index ->
                    PetCardItem(petInfo = petList[index], navigateToDetailsScreen)
                }
            })

    }
}


@Composable
fun CustomHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(46.dp)
                    .padding(10.dp),
                painter = painterResource(id = R.drawable.ic_menu),
                tint=MaterialTheme.colors.onPrimary,
                contentDescription = ""
            )
            Image(
                painter = painterResource(R.drawable.user_profile),
                contentDescription = "avatar",
                contentScale = ContentScale.Inside,

                modifier = Modifier
                    .size(30.dp)
                    .border(1.dp, Color.Gray, CircleShape)
            )

        }

        Text(text = "Hello, Samantha", style = MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.onPrimary))
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Pets are waiting for you", style = MaterialTheme.typography.h1.copy(color = MaterialTheme.colors.onPrimary))
        Spacer(modifier = Modifier.height(10.dp))
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color =  Color(0xFFF5F5F5),
                    shape = CircleShape
                )
                .height(50.dp),

            )
        Spacer(modifier = Modifier.height(10.dp))

    }


}

@Composable
fun PetFilter(
    selectedCategory: AnimalCategory?,
    onSelectedChanged: (String) -> Unit
) {

    LazyRow(
        content = {
            items(getAllAnimalCategory().size) { index ->

                PetCard(
                    getAllAnimalCategory()[index],
                    selectedCategory == getAllAnimalCategory()[index],
                    onSelectedCategoryChange = {
                        onSelectedChanged(it)
                    },
                    icon= getAllAnimalIcon()[index]
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()

            .padding(start = 10.dp, end = 10.dp),


        )
}

@Composable
fun PetCard(
    animalType: AnimalCategory,
    isSelected: Boolean = false,
    onSelectedCategoryChange: (String) -> Unit,
    icon: Int,
) {
    Surface(
        modifier = Modifier
            .padding(end = 10.dp).height(40.dp).border(1.dp ,color = if(isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSecondary, shape = RoundedCornerShape(2.dp))
           ,
        elevation = 3.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) Color.Black else MaterialTheme.colors.surface
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.toggleable(value = isSelected, onValueChange = {
                onSelectedCategoryChange(animalType.value)


            })
        ) {
            val image = loadImageFromDrawable(defaultImage = icon).value
            image?.let {
                Icon( modifier = Modifier.size(30.dp).padding(end=5.dp,start = 8.dp),
                    tint=if (isSelected) Color.White else Color(0xFF666666),
                   bitmap = it.asImageBitmap(),contentDescription = ""
                )
            }
            Text(
                modifier = Modifier.padding(8.dp),
                text = animalType.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.button.copy(color = if (isSelected) Color.White else Color(0xFF666666)),
            )

        }

    }
}

@Composable
private fun CustomTextField(
    modifier: Modifier = Modifier,
) {
    var name by remember { mutableStateOf("Search Your Friend") }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {

        TextField(
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(24.dp)
                )
            },
            enabled = false,


            modifier = Modifier.background(color = Color.Transparent),
            value = name,
            placeholder = { Text(text = "Search Here", textAlign = TextAlign.Start) },

            onValueChange = { name = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
            )


        )


    }
}


@Composable
fun loadImageFromDrawable( @DrawableRes defaultImage: Int): MutableState<Bitmap?> {
    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)
    Glide.with(LocalContext.current).asBitmap().load(defaultImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value =resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })

    return  bitmapState
}

