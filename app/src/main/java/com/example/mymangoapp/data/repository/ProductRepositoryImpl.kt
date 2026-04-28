package com.example.mymangoapp.data.repository

import com.example.mymangoapp.data.local.dao.FavoriteDao
import com.example.mymangoapp.data.local.entity.toDomain
import com.example.mymangoapp.data.local.entity.toEntity
import com.example.mymangoapp.data.remote.api.FakeStoreApi
import com.example.mymangoapp.data.remote.dto.toDomain
import com.example.mymangoapp.domain.model.Product
import com.example.mymangoapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: FakeStoreApi,
    private val favoriteDao: FavoriteDao
) : ProductRepository {

    override suspend fun getProducts(): Result<List<Product>> {
        return try {
            val favoriteIds = favoriteDao.getAllFavoriteIds()
            val products = api.getProducts().map { dto ->
                dto.toDomain(isFavorite = dto.id in favoriteIds)
            }
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getFavorites(): Flow<List<Product>> {
        return favoriteDao.getAllFavorites().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun toggleFavorite(product: Product) {
        if (favoriteDao.isFavorite(product.id)) {
            favoriteDao.deleteFavorite(product.toEntity())
        } else {
            favoriteDao.insertFavorite(product.toEntity())
        }
    }

    override suspend fun isFavorite(productId: Int): Boolean {
        return favoriteDao.isFavorite(productId)
    }
}
