package org.adaschool.taskplanner.network.service.auth

import okhttp3.Interceptor
import okhttp3.Response
import org.adaschool.taskplanner.storage.Storage

/**
 * @author Santiago Carrillo
 * 27/10/21.
 */
class AuthInterceptor(private val storage: Storage) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = storage.getToken()
        if (token.isNotEmpty()) {
            request.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(request.build())
    }
}