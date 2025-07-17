package com.example.simfan.data.model

data class ProfileResponse(
    val id: String,
    val name: String,
    val email: String,
    val kodeReferal: String,
    val points: Int,
    val expiry: String,
    val avatarUrl: String? = null
)
