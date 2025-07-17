package com.example.simfan.di

import com.example.simfan.data.remote.SimFanApiService
import com.example.simfan.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideProfileRepository(api: SimFanApiService) = ProfileRepository(api)

    @Singleton
    @Provides
    fun providePromoRepository(api: SimFanApiService) = PromoRepository(api)

    @Singleton
    @Provides
    fun provideProductRepository(api: SimFanApiService) = ProductRepository(api)

    @Singleton
    @Provides
    fun provideDepositoRepository(api: SimFanApiService) = DepositoRepository(api)

    @Singleton
    @Provides
    fun provideArticleRepository(api: SimFanApiService) = ArticleRepository(api)
}
