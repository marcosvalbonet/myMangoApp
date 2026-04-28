package com.example.mymangoapp.domain.model

data class User(
    val id: Int,
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val city: String,
    val street: String,
    val zipcode: String
)
