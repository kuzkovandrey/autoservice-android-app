package com.example.autoservice.di

import com.example.autoservice.common.constants.ApiControllers
import com.example.autoservice.data.remote.OrderApi
import com.example.autoservice.data.repository.OrderRepositoryImpl
import com.example.autoservice.domain.repository.OrderRepository
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
    fun provideOrderApi(): OrderApi {
        return Retrofit.Builder()
            .baseUrl(ApiControllers.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OrderApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOrderRepository(api: OrderApi): OrderRepository {
        return OrderRepositoryImpl(api)
    }

}