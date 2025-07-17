package com.example.simfan.data.repository

import com.example.simfan.data.model.ProductResponse
import com.example.simfan.data.remote.SimFanApiService

class ProductRepository(
    private val api: SimFanApiService
) {
    suspend fun getProducts(token: String): List<ProductResponse> {
        return api.getProducts(token)
    }
    suspend fun getProductDetail(token: String, id: String): ProductResponse {
        return api.getProductDetail(token, id)
    }

}
