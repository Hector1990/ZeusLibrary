package com.wolfspider.zeus.sdk.system.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> saveValueToSharedPreferencesWithEncrypted(
        context: Context,
        name: String,
        key: String,
        value: T
) {
    saveValueToSharedPreferences(
            EncryptedSharedPreferences.create(
                    name,
                    MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            ),
            key,
            value
    )
}

inline fun <reified T> saveValueToSharedPreferences(
        context: Context,
        name: String,
        key: String,
        value: T
) {
    saveValueToSharedPreferences(
            context.getSharedPreferences(name, Context.MODE_PRIVATE),
            key,
            value
    )
}

inline fun <reified T> saveValueToSharedPreferences(
        sharedPreferences: SharedPreferences,
        key: String,
        value: T
) {
    with(sharedPreferences.edit()) {
        when (T::class) {
            Int::class -> putInt(key, value as Int)
            Long::class -> putLong(key, value as Long)
            Float::class -> putFloat(key, value as Float)
            Boolean::class -> putBoolean(key, value as Boolean)
            String::class -> putString(key, value as String)
            else -> putString(key, Gson().toJson(value, T::class.java))
        }
        apply()
    }
}

inline fun <reified T> getValueFromSharedPreferences(
        context: Context,
        name: String,
        key: String,
        default: T
): T? {
    with(context.getSharedPreferences(name, Context.MODE_PRIVATE)) {
        return when (T::class) {
            Int::class -> getInt(key, default as Int) as T
            Long::class -> getLong(key, default as Long) as T
            Float::class -> getFloat(key, default as Float) as T
            Boolean::class -> getBoolean(key, default as Boolean) as T
            String::class -> getString(key, default as String) as T
            else -> {
                val text = getString(key, "")
                if (text.isNullOrBlank()) {
                    null
                } else {
                    Gson().fromJson(text, object : TypeToken<T>() {}.type)
                }
            }
        }
    }
}
