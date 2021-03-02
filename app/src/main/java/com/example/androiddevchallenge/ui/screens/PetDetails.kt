package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.PetModel
import com.example.androiddevchallenge.ui.component.OwnerInfo
import com.example.androiddevchallenge.ui.component.PetAttribute
import com.example.androiddevchallenge.ui.component.loadImage
import com.example.androiddevchallenge.ui.theme.GreenColor
import com.example.androiddevchallenge.ui.theme.heartLikeColor


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
                        val image = loadImage(url = it, defaultImage = R.drawable.ic_pet).value

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

