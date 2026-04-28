package com.example.mymangoapp.di

import com.example.mymangoapp.data.repository.ProductRepositoryImpl
import com.example.mymangoapp.data.repository.UserRepositoryImpl
import com.example.mymangoapp.domain.repository.ProductRepository
import com.example.mymangoapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}
