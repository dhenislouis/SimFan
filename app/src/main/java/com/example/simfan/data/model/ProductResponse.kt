package com.example.simfan.data.model

data class ProductResponse(
    val id: String,
    val title: String,
    val description: String,
    val location: String,
    val minimum: String,
    val tenor: String,
    val bunga: String,
    val imageUrl: String?
)
