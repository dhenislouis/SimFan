package com.example.simfan.ui.promo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simfan.data.model.PromoResponse
import com.example.simfan.data.repository.PromoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PromoViewModel @Inject constructor(
    private val repository: PromoRepository
) : ViewModel() {

    private val _promoState = MutableStateFlow<List<PromoResponse>>(emptyList())
    val promoState: StateFlow<List<PromoResponse>> = _promoState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun fetchPromos(token: String) {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                val response = repository.getPromos(token)
                _promoState.value = response
            } catch (e: Exception) {
                _errorState.value = e.message ?: "Terjadi kesalahan"
            } finally {
                _loadingState.value = false
            }
        }
    }
}
