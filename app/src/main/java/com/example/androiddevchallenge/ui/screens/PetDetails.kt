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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.PetModel
import com.example.androiddevchallenge.ui.component.OwnerInfo
import com.example.androiddevchallenge.ui.component.PetAttribute
import com.example.androiddevchallenge.ui.component.LoadImageFromUrl
import com.example.androiddevchallenge.ui.theme.GreenColor
import com.example.androiddevchallenge.ui.theme.heartLikeColor
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Button


@Composable
fun PetDetails(pet: PetModel, navigateBack: () -> Unit) {
    val gender = if (pet.isFemale) "Female" else "Male"
    val heartIcon =
        if (pet.isLiked) R.drawable.ic_heart_active else R.drawable.ic_heart_inactive

    Scaffold(
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(5.dp)
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(.7f)
                        .height(50.dp),
                    onClick = {}, colors = ButtonDefaults.textButtonColors(
                        backgroundColor = MaterialTheme.colors.secondary
                    )
                ) {
                    Text(
                        text = "Adopt",
                        style = MaterialTheme.typography.button.copy(color = MaterialTheme.colors.background))

                }

                IconButton(onClick = navigateBack) {
                    Row(modifier = Modifier.padding(end=10.dp)) {
                        Icon(
                            modifier=Modifier.size(36.dp),
                            painter = painterResource(id = R.drawable.ic_phone_call),
                            tint= GreenColor,
                            contentDescription = ""
                        )
                    }

                }
            }
        },

        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        color = MaterialTheme.colors.background,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                        .align(alignment = Alignment.CenterHorizontally)
                ) {
                    pet.picture.let {
                        val image = LoadImageFromUrl(url = it, defaultImage = R.drawable.ic_pet).value

                        image?.let {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f),
                                contentScale = ContentScale.Crop,

                                bitmap = it.asImageBitmap(),
                                contentDescription = ""
                            )
                        }

                        Row {
                            IconButton(onClick = navigateBack) {
                                Icon(

                                    painter = painterResource(id = R.drawable.ic_round_back),
                                    contentDescription = ""
                                )
                            }

                        }


                    }


                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colors.background,
                            shape = RoundedCornerShape(topEnd = 16.dp, topStart = 14.dp)
                        )

                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Column {
                            Text(
                                pet.name,
                                style = MaterialTheme.typography.h1.copy(color = MaterialTheme.colors.onPrimary)
                            )

                            Text(
                                pet.breedName,
                                style = MaterialTheme.typography.subtitle2.copy(color = MaterialTheme.colors.onPrimary)
                            )
                        }

                        IconButton(
                            onClick = {}, modifier = Modifier
                                .height(24.dp)
                                .width(24.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = heartIcon),
                                contentDescription = "",
                                tint = heartLikeColor
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        PetAttribute("Age", pet.age)
                        PetAttribute("Weight", pet.weight.toString())
                        PetAttribute("Sex", gender)
                        PetAttribute("Type", pet.animalType)
                    }
                    OwnerInfo(pet)
                    Text(
                        text = pet.description,
                        style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onPrimary,lineHeight = 22.sp),
                        modifier = Modifier.padding(10.dp),
                    )
                    Spacer(modifier = Modifier.height(60.dp))


                }


            }
        },
    )

}


