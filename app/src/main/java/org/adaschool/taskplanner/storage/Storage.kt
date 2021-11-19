package org.adaschool.taskplanner.storage

/**
 * @author Santiago Carrillo
 * 19/10/21.
 */
interface Storage {

    fun saveToken(token: String)

    fun getToken(): String

    fun clear()

    fun isUserAuth(): Boolean
}