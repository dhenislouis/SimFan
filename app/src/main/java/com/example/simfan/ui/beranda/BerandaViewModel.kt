package com.example.simfan.ui.beranda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simfan.data.model.ArticleResponse
import com.example.simfan.data.model.PromoResponse
import com.example.simfan.data.model.ProductResponse
import com.example.simfan.data.model.DepositoResponse
import com.example.simfan.data.repository.ArticleRepository
import com.example.simfan.data.repository.PromoRepository
import com.example.simfan.data.repository.ProductRepository
import com.example.simfan.data.repository.DepositoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BerandaViewModel @Inject constructor(
    private val promoRepository: PromoRepository,
    private val productRepository: ProductRepository,
    private val depositoRepository: DepositoRepository,
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _promoState = MutableStateFlow<List<PromoResponse>>(emptyList())
    val promoState: StateFlow<List<PromoResponse>> = _promoState

    private val _produkState = MutableStateFlow<List<ProductResponse>>(emptyList())
    val produkState: StateFlow<List<ProductResponse>> = _produkState

    private val _depositoState = MutableStateFlow<List<DepositoResponse>>(emptyList())
    val depositoState: StateFlow<List<DepositoResponse>> = _depositoState

    private val _articleState = MutableStateFlow<List<ArticleResponse>>(emptyList())
    val articleState: StateFlow<List<ArticleResponse>> = _articleState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun fetchBerandaData(token: String) {
        viewModelScope.launch {
            _loadingState.value = true
            _errorState.value = null
            try {
                val promos = promoRepository.getPromos(token)
                val products = productRepository.getProducts(token)
                val depositos = depositoRepository.getDepositos(token)
                val articles = articleRepository.getArticles(token)

                _promoState.value = promos
                _produkState.value = products
                _depositoState.value = depositos
                _articleState.value = articles

            } catch (e: Exception) {
                _errorState.value = e.message ?: "Terjadi kesalahan saat memuat data beranda"
            } finally {
                _loadingState.value = false
            }
        }
    }
}
