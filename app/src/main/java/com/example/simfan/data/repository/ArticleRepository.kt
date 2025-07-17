package com.example.simfan.data.repository

import com.example.simfan.data.model.ArticleResponse
import com.example.simfan.data.remote.SimFanApiService

class ArticleRepository(
    private val api: SimFanApiService
) {
    suspend fun getArticles(token: String): List<ArticleResponse> {
        return api.getArticles(token)
    }
}
