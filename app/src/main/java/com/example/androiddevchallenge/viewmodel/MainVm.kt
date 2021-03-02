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
package com.example.androiddevchallenge.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.AnimalCategory
import com.example.androiddevchallenge.data.FakePuppies
import com.example.androiddevchallenge.data.PetModel
import com.example.androiddevchallenge.data.getAnimalCategory
import kotlinx.coroutines.launch

class MainVm() : ViewModel() {
    val petList: MutableState<List<PetModel>> = mutableStateOf(listOf())
    val selectedValueCategory: MutableState<AnimalCategory?> = mutableStateOf(AnimalCategory.DOG)

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            val result = FakePuppies
            petList.value = result.filter { item -> item.animalType.equals("Dog", ignoreCase = true) }
        }
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getAnimalCategory(category)
        selectedValueCategory.value = newCategory
        onValueChange(category)
    }

    fun onValueChange(category: String) {
        viewModelScope.launch {
            val result = FakePuppies
            petList.value = result.filter { item -> item.animalType.equals(category, ignoreCase = true) }
        }
    }
}
