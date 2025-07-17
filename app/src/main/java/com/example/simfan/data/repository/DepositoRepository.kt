package com.example.simfan.data.repository

import com.example.simfan.data.model.DepositoResponse
import com.example.simfan.data.remote.SimFanApiService

class DepositoRepository(
    private val api: SimFanApiService
) {
    suspend fun getDepositos(token: String): List<DepositoResponse> {
        return api.getDepositos(token)
    }
    suspend fun getDepositoDetail(token: String, id: String): DepositoResponse {
        return api.getDepositoDetail(token, id)
    }

}
