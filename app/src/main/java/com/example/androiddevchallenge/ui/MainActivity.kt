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
package com.example.androiddevchallenge.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.screens.PetDetails
import com.example.androiddevchallenge.ui.screens.PetList
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.utils.Navigation
import com.example.androiddevchallenge.viewmodel.MainVm

class MainActivity : AppCompatActivity() {
    private val mainVm: MainVm by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            MyTheme( ) {
                PetApp(mainVm)
            }
        }
    }
}


@Composable
fun PetApp(mainVm: MainVm) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Navigation.PetList.title) {
        composable(Navigation.PetList.title) {
            PetList(
                selectedCategory = mainVm.selectedValueCategory.value,
                petList = mainVm.petList.value,
                onSelectedChanged = mainVm::onSelectedCategoryChanged,

                navigateToDetailsScreen = { pet ->
                    navController.navigate(Navigation.Details.title + "/${pet.id}")
                })
        }
        composable(Navigation.Details.title + "/{id}") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("id")
            val doggo = mainVm.petList.value.find { it.id == petId }
                ?: throw IllegalStateException("Doggo not found")
            PetDetails(
                pet = doggo,
                navigateBack = { navController.popBackStack() }
            )
        }
    }

}
