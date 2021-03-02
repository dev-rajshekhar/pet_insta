package com.example.androiddevchallenge.utils

sealed class Navigation(val title: String) {
    object PetList : Navigation("PetList")
    object Details : Navigation("Details")
}