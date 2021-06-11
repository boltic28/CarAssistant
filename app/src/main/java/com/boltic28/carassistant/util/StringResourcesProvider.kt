package com.boltic28.carassistant.util

import android.content.Context
import androidx.annotation.StringRes

class StringResourcesProvider(private val context: Context) {

    fun get(@StringRes stringId: Int): String = context.getString(stringId)

    fun get(@StringRes stringId: Int, vararg args: Any?): String = context.getString(stringId, *args)
}