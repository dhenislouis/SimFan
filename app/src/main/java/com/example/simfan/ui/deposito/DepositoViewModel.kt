package com.example.simfan.ui.deposito

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simfan.data.model.DepositoResponse
import com.example.simfan.data.repository.DepositoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DepositoViewModel @Inject constructor(
    private val repository: DepositoRepository
) : ViewModel() {

    private val _depositoState = MutableStateFlow<List<DepositoResponse>>(emptyList())
    val depositoState: StateFlow<List<DepositoResponse>> = _depositoState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun fetchDepositos(token: String) {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                val response = repository.getDepositos(token)
                _depositoState.value = response
            } catch (e: Exception) {
                _errorState.value = e.message ?: "Terjadi kesalahan"
            } finally {
                _loadingState.value = false
            }
        }
    }
}
