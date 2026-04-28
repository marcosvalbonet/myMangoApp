package com.example.mymangoapp.data.remote.api

import com.example.mymangoapp.data.remote.dto.ProductDto
import com.example.mymangoapp.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApi {

    @GET("products")
    suspend fun getProducts(): List<ProductDto>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserDto
}
