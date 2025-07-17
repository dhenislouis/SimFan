package com.example.simfan.data.repository

import com.example.simfan.data.model.ProfileResponse
import com.example.simfan.data.remote.SimFanApiService

class ProfileRepository(
    private val api: SimFanApiService
) {
    suspend fun getProfile(token: String): ProfileResponse {
        return api.getProfile(token)
    }
}
