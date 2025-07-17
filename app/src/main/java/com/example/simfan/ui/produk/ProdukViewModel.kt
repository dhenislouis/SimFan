package com.example.simfan.ui.produk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simfan.data.model.ProductResponse
import com.example.simfan.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProdukViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _produkState = MutableStateFlow<List<ProductResponse>>(emptyList())
    val produkState: StateFlow<List<ProductResponse>> = _produkState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun fetchProducts(token: String) {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                val response = repository.getProducts(token)
                _produkState.value = response
            } catch (e: Exception) {
                _errorState.value = e.message ?: "Terjadi kesalahan"
            } finally {
                _loadingState.value = false
            }
        }
    }
}
