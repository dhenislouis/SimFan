package com.example.simfan.di

import android.provider.SyncStateContract
import com.example.simfan.data.remote.SimFanApiService
import com.example.simfan.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SyncStateContract.Constants.BASE_URL) // e.g., "https://api.simfan.id/"
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSimFanApiService(retrofit: Retrofit): SimFanApiService {
        return retrofit.create(SimFanApiService::class.java)
    }
}
