package com.example.simfan.data.remote

import com.example.simfan.data.model.*
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SimFanApiService {

    @GET("profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): ProfileResponse

    @GET("promo")
    suspend fun getPromos(
        @Header("Authorization") token: String
    ): List<PromoResponse>

    @GET("produk")
    suspend fun getProducts(
        @Header("Authorization") token: String
    ): List<ProductResponse>

    @GET("deposito")
    suspend fun getDepositos(
        @Header("Authorization") token: String
    ): List<DepositoResponse>

    @GET("beranda/articles")
    suspend fun getArticles(
        @Header("Authorization") token: String
    ): List<ArticleResponse>

    // Optional: Detail endpoints if needed
    @GET("promo/{id}")
    suspend fun getPromoDetail(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): PromoResponse

    @GET("produk/{id}")
    suspend fun getProductDetail(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): ProductResponse

    @GET("deposito/{id}")
    suspend fun getDepositoDetail(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): DepositoResponse
}
