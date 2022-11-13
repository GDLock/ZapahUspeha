package com.otkritie.hackaton.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.otkritie.hackaton.data.local.preference.AuthPreference
import com.otkritie.hackaton.data.local.preference.AuthPreferenceImpl
import com.otkritie.hackaton.data.manager.AuthManager
import com.otkritie.hackaton.data.remote.ChatApi
import com.otkritie.hackaton.data.remote.interceptor.AuthInterceptor
import com.otkritie.hackaton.data.remote.interceptor.RestParamsInterceptor
import com.otkritie.hackaton.data.remote.serializer.RoleAsStringSerializer
import com.otkritie.hackaton.data.remote.websocket.WebSocketClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import javax.inject.Singleton

@OptIn(ExperimentalSerializationApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        serializersModule = SerializersModule {
            contextual(RoleAsStringSerializer)
        }
    }

    @Provides
    @Singleton
    fun provideStockApi(okHttpClient: OkHttpClient): ChatApi {
        return Retrofit.Builder()
            .baseUrl(ChatApi.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
            .create(ChatApi::class.java)
    }

    @Provides
    fun provideOkHttpClient(authManager: AuthManager): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(authManager))
            .addInterceptor(RestParamsInterceptor(json))
            .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
            .build()
    }

    @Provides
    @Singleton
    fun provideWebSocketClient(authManager: AuthManager): WebSocketClient {
        return WebSocketClient(json, authManager, ChatApi.WEBSOCKET_URL)
    }

    @Provides
    @Singleton
    fun provideAuthPreference(@ApplicationContext context: Context): AuthPreference {
        val sharedPreference = context.getSharedPreferences(AUTH_PREFERENCE, MODE_PRIVATE)
        return AuthPreferenceImpl(sharedPreference)
    }

    private const val AUTH_PREFERENCE = "auth_preference_key"
}
