package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.*
import com.example.androiddevchallenge.ui.component.PetCardItem
import com.example.androiddevchallenge.ui.component.loadImageDrawable
import com.example.androiddevchallenge.ui.theme.GreyText
import com.example.androiddevchallenge.ui.theme.grey
import com.example.androiddevchallenge.ui.theme.greyLight


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
                    color = greyLight,
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
            val image = loadImageDrawable(defaultImage = icon).value
            image?.let {
                Icon( modifier = Modifier.size(30.dp).padding(end=5.dp,start = 8.dp),
                    tint=if (isSelected) Color.White else GreyText,
                   bitmap = it.asImageBitmap(),contentDescription = ""
                )
            }
            Text(
                modifier = Modifier.padding(8.dp),
                text = animalType.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.button.copy(color = if (isSelected) Color.White else GreyText),
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

