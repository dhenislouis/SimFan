package com.example.simfan.ui.produk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simfan.data.model.ProductResponse
import com.example.simfan.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProdukDetailViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _produkDetailState = MutableStateFlow<ProductResponse?>(null)
    val produkDetailState: StateFlow<ProductResponse?> = _produkDetailState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun fetchProdukDetail(token: String, id: String) {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                val detail = repository.getProductDetail(token, id)
                _produkDetailState.value = detail
            } catch (e: Exception) {
                _errorState.value = e.message ?: "Gagal memuat detail produk"
            } finally {
                _loadingState.value = false
            }
        }
    }
}
