package com.otkritie.hackaton.data.manager

import com.otkritie.hackaton.data.local.preference.AuthPreference
import com.otkritie.hackaton.data.manager.AuthManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManagerImpl @Inject constructor(
    private val authPreference: AuthPreference
): AuthManager {

    override val token get() = authPreference.token
}
