package org.adaschool.taskplanner.storage

import android.content.SharedPreferences
import org.adaschool.taskplanner.utils.TOKEN_KEY

/**
 * @author Santiago Carrillo
 * 19/10/21.
 */
class SharedPreferencesStorage(private val sharedPreferences: SharedPreferences) : Storage {


    override fun saveToken(token: String) {
        if (token == "exception") {
            throw RuntimeException()
        }
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    override fun getToken(): String {
        return sharedPreferences.getString(TOKEN_KEY, "")!!
    }

    override fun clear() {
        return sharedPreferences.edit()
            .remove(TOKEN_KEY)
            .apply()
    }

    override fun isUserAuth(): Boolean {
        return getToken().isNotEmpty()
    }
}