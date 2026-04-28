package com.example.mymangoapp.domain.repository

import com.example.mymangoapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    fun getFavorites(): Flow<List<Product>>
    suspend fun toggleFavorite(product: Product)
    suspend fun isFavorite(productId: Int): Boolean
}
