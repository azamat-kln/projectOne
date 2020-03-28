package com.example.weatherapp1.network

data class Mocky(
    val email: String,
    val employment: Employment,
    val first_name: String,
    val gender: String,
    val id: Int,
    val ip_address: String,
    val last_name: String,
    val photo: String
)

data class Employment(
    val name: String,
    val position: String
)