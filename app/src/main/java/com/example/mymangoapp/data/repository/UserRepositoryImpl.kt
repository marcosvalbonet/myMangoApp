package com.example.mymangoapp.data.repository

import com.example.mymangoapp.data.remote.api.FakeStoreApi
import com.example.mymangoapp.data.remote.dto.toDomain
import com.example.mymangoapp.domain.model.User
import com.example.mymangoapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: FakeStoreApi
) : UserRepository {

    override suspend fun getUserProfile(): Result<User> {
        return try {
            val user = api.getUserById(id = 8).toDomain()
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
