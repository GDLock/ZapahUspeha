package com.otkritie.hackaton.data.local.preference

import android.content.SharedPreferences
import com.otkritie.hackaton.data.remote.model.Role
import javax.inject.Singleton

@Singleton
class AuthPreferenceImpl(
    private val preference: SharedPreferences
) : AuthPreference {

    override var token: String?
        get() = preference.getString(TOKEN_KEY, null)
        set(value) {
            setString(TOKEN_KEY, value)
        }

    override var name: String
        get() = preference.getString(NAME_KEY, "") ?: ""
        set(value) {
            setString(NAME_KEY, value)
        }

    override var surname: String
        get() = preference.getString(SURNAME_KEY, "") ?: ""
        set(value) {
            setString(SURNAME_KEY, value)
        }

    override var middleName: String
        get() = preference.getString(MIDDLE_NAME_KEY, "") ?: ""
        set(value) {
            setString(MIDDLE_NAME_KEY, value)
        }

    override var role: Role
        get() = Role.valueOf(preference.getString(ROLE_KEY, Role.CLIENT.name) ?: Role.CLIENT.name)
        set(value) {
            setString(ROLE_KEY, value.name)
        }

    override var id: Int
        get() = preference.getInt(ID_KEY, 0)
        set(value) { preference.edit().putInt(ID_KEY, value).apply()}

    override fun clear() {
        preference.edit().clear().apply()
    }

    private fun setString(key: String, value: String?) {
        preference.edit().putString(key, value).apply()
    }

    private companion object {
        const val TOKEN_KEY = "token"
        const val NAME_KEY = "name"
        const val SURNAME_KEY = "surname"
        const val MIDDLE_NAME_KEY = "middle_name"
        const val ROLE_KEY = "role"
        const val ID_KEY = "id"
    }
}
