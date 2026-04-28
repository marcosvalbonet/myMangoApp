package com.example.mymangoapp.domain.usecase

import com.example.mymangoapp.domain.model.Product
import com.example.mymangoapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> = repository.getFavorites()
}
