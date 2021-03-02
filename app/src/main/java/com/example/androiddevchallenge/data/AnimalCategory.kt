package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.R

enum class AnimalCategory(val value: String) {
    DOG("Dog"),
    CAT("Cat"),
    PARROT("Parrot"),
    BUNNY("Bunny"),


}

fun getAllAnimalCategory(): List<AnimalCategory> {
    return listOf(
        AnimalCategory.DOG,
        AnimalCategory.CAT,
        AnimalCategory.PARROT,
        AnimalCategory.BUNNY,
    )

}

fun getAnimalCategory(value: String): AnimalCategory? {
    val map = AnimalCategory.values().associateBy(AnimalCategory::value)
    return map[value]
}

fun getAllAnimalIcon() =
    listOf<Int>(R.drawable.ic_dog, R.drawable.ic_cat, R.drawable.ic_dove, R.drawable.ic_rabbit)