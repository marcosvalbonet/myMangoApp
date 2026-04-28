package com.example.mymangoapp.domain.usecase

import com.example.mymangoapp.domain.model.Product
import com.example.mymangoapp.domain.repository.ProductRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product) = repository.toggleFavorite(product)
}
