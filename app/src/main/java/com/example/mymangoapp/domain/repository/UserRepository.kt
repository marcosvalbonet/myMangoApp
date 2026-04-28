package com.example.mymangoapp.domain.repository

import com.example.mymangoapp.domain.model.User

interface UserRepository {
    suspend fun getUserProfile(): Result<User>
}
