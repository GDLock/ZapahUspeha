package com.otkritie.hackaton.core

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getString(@StringRes resource: Int) = context.getString(resource)

    fun getColor(@ColorRes resource: Int) = ContextCompat.getColor(context, resource)

    fun getDrawable(@DrawableRes resource: Int) = AppCompatResources.getDrawable(context, resource)
}
