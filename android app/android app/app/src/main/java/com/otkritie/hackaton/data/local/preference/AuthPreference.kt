package com.otkritie.hackaton.data.local.preference

import com.otkritie.hackaton.data.remote.model.Role

interface AuthPreference {

    var token: String?

    var name: String

    var surname: String

    var middleName: String

    var role: Role

    var id: Int

    fun clear()
}
