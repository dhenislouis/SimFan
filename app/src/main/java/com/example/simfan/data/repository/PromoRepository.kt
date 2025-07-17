package com.example.simfan.data.repository

import com.example.simfan.data.model.PromoResponse
import com.example.simfan.data.remote.SimFanApiService

class PromoRepository(
    private val api: SimFanApiService
) {
    suspend fun getPromos(token: String): List<PromoResponse> {
        return api.getPromos(token)
    }
    suspend fun getPromoDetail(token: String, id: String): PromoResponse {
        return api.getPromoDetail(token, id)
    }
}
