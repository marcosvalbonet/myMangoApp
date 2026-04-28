package com.example.mymangoapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymangoapp.domain.model.User
import com.example.mymangoapp.domain.repository.ProductRepository
import com.example.mymangoapp.domain.usecase.GetUserProfileUseCase
import com.example.mymangoapp.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val userState: UiState<User> = UiState.Loading,
    val favoritesCount: Int = 0
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    productRepository: ProductRepository
) : ViewModel() {

    private val _userState = MutableStateFlow<UiState<User>>(UiState.Loading)

    val uiState: StateFlow<ProfileUiState> = combine(
        _userState,
        productRepository.getFavorites()
    ) { userState, favorites ->
        ProfileUiState(
            userState = userState,
            favoritesCount = favorites.size
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ProfileUiState()
    )

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _userState.update { UiState.Loading }
            getUserProfileUseCase()
                .onSuccess { user -> _userState.update { UiState.Success(user) } }
                .onFailure { error -> _userState.update { UiState.Error(error.message ?: "Error") } }
        }
    }
}
