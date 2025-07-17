package com.example.simfan.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simfan.data.model.ProfileResponse
import com.example.simfan.data.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _profileState = MutableStateFlow<ProfileResponse?>(null)
    val profileState: StateFlow<ProfileResponse?> = _profileState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun fetchProfile(token: String) {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                val response = repository.getProfile(token)
                _profileState.value = response
            } catch (e: Exception) {
                _errorState.value = e.message ?: "Terjadi kesalahan"
            } finally {
                _loadingState.value = false
            }
        }
    }
}
