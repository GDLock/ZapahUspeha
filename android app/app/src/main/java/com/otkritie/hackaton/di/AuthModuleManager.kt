package com.otkritie.hackaton.di

import com.otkritie.hackaton.data.manager.AuthManager
import com.otkritie.hackaton.data.manager.AuthManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthModuleManager {

    @Binds
    fun authManager(impl: AuthManagerImpl): AuthManager
}
