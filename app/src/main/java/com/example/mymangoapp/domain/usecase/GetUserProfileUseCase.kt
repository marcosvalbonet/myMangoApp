package com.example.mymangoapp.domain.usecase

import com.example.mymangoapp.domain.model.User
import com.example.mymangoapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Result<User> = repository.getUserProfile()
}
