package com.example.mymangoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymangoapp.domain.model.Product
import com.example.mymangoapp.domain.model.Rating

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val ratingRate: Double,
    val ratingCount: Int
)

fun FavoriteEntity.toDomain() = Product(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    rating = Rating(rate = ratingRate, count = ratingCount),
    isFavorite = true
)

fun Product.toEntity() = FavoriteEntity(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    ratingRate = rating.rate,
    ratingCount = rating.count
)
