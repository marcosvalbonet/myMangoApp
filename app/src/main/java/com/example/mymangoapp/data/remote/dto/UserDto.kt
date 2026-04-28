package com.example.mymangoapp.data.remote.dto

import com.example.mymangoapp.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("username") val username: String,
    @SerializedName("name") val name: NameDto,
    @SerializedName("phone") val phone: String,
    @SerializedName("address") val address: AddressDto
)

data class NameDto(
    @SerializedName("firstname") val firstName: String,
    @SerializedName("lastname") val lastName: String
)

data class AddressDto(
    @SerializedName("city") val city: String,
    @SerializedName("street") val street: String,
    @SerializedName("zipcode") val zipcode: String
)

fun UserDto.toDomain() = User(
    id = id,
    email = email,
    username = username,
    firstName = name.firstName,
    lastName = name.lastName,
    phone = phone,
    city = address.city,
    street = address.street,
    zipcode = address.zipcode
)
