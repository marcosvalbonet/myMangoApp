package com.example.mymangoapp.domain.usecase

import com.example.mymangoapp.domain.model.Product
import com.example.mymangoapp.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): Result<List<Product>> = repository.getProducts()
}
