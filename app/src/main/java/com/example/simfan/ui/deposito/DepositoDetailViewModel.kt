package com.example.simfan.ui.deposito

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simfan.data.model.DepositoResponse
import com.example.simfan.data.repository.DepositoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepositoDetailViewModel @Inject constructor(
    private val repository: DepositoRepository
) : ViewModel() {

    private val _depositoDetailState = MutableStateFlow<DepositoResponse?>(null)
    val depositoDetailState: StateFlow<DepositoResponse?> = _depositoDetailState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun fetchDepositoDetail(token: String, id: String) {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                val detail = repository.getDepositoDetail(token, id)
                _depositoDetailState.value = detail
            } catch (e: Exception) {
                _errorState.value = e.message ?: "Gagal memuat detail deposito"
            } finally {
                _loadingState.value = false
            }
        }
    }
}
