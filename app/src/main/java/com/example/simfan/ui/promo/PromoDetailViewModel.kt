package com.example.simfan.ui.promo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simfan.data.model.PromoResponse
import com.example.simfan.data.repository.PromoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromoDetailViewModel @Inject constructor(
    private val repository: PromoRepository
) : ViewModel() {

    private val _promoDetailState = MutableStateFlow<PromoResponse?>(null)
    val promoDetailState: StateFlow<PromoResponse?> = _promoDetailState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun fetchPromoDetail(token: String, id: String) {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                val detail = repository.getPromoDetail(token, id)
                _promoDetailState.value = detail
            } catch (e: Exception) {
                _errorState.value = e.message ?: "Gagal memuat detail promo"
            } finally {
                _loadingState.value = false
            }
        }
    }
}
