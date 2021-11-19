package org.adaschool.taskplanner.network.service.auth

import org.adaschool.taskplanner.network.service.auth.dto.LoginDto
import org.adaschool.taskplanner.network.service.auth.dto.TokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Santiago Carrillo
 * 19/10/21.
 */
interface AuthService {

    @POST("auth")
    suspend fun auth(@Body loginDto: LoginDto): Response<TokenDto>

}