package com.asct94.securenote.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesApp @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val sharedPref = context.getSharedPreferences("SecureNotePrefs", Context.MODE_PRIVATE)

    var appBiometrics: Boolean
        get() = sharedPref.getBoolean(APP_BIOMETRICS, false)
        set(value) {
            sharedPref.edit().putBoolean(APP_BIOMETRICS, value).apply()
        }

    var password: String?
        get() = sharedPref.getString(APP_PASSWORD, "").takeUnless { it.isNullOrEmpty() }
        set(value) {
            sharedPref.edit().putString(APP_PASSWORD, value).apply()
        }

    companion object {
        const val APP_BIOMETRICS = "APP_BIOMETRICS"
        const val APP_PASSWORD = "APP_PASSWORD"
        const val DEFAULT_PASSWORD = "1234"
    }
}