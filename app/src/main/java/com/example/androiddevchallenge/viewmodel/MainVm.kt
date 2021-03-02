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
import java.util.*

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