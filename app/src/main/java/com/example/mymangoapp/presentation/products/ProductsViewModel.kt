package com.example.mymangoapp.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymangoapp.domain.model.Product
import com.example.mymangoapp.domain.usecase.GetProductsUseCase
import com.example.mymangoapp.domain.usecase.ToggleFavoriteUseCase
import com.example.mymangoapp.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Product>>> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.update { UiState.Loading }
            getProductsUseCase()
                .onSuccess { products ->
                    _uiState.update { UiState.Success(products) }
                }
                .onFailure { error ->
                    _uiState.update { UiState.Error(error.message ?: "Error desconocido") }
                }
        }
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            toggleFavoriteUseCase(product)
            // Refresh list to update favorite states
            val currentState = _uiState.value
            if (currentState is UiState.Success) {
                val updatedList = currentState.data.map {
                    if (it.id == product.id) it.copy(isFavorite = !it.isFavorite) else it
                }
                _uiState.update { UiState.Success(updatedList) }
            }
        }
    }
}
