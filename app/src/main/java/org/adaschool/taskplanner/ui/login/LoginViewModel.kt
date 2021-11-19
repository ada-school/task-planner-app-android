package org.adaschool.taskplanner.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.adaschool.taskplanner.network.service.auth.AuthService
import org.adaschool.taskplanner.network.service.auth.dto.LoginDto
import org.adaschool.taskplanner.storage.Storage
import javax.inject.Inject

/**
 * @author Santiago Carrillo
 * 25/10/21.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService,
    private val storage: Storage
) : ViewModel() {

    val labelLiveData = MutableLiveData<String>()

    val requestResultLiveData = MutableLiveData<Boolean>()

    fun auth(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authService.auth(LoginDto(email, password))

            if (response.isSuccessful) {
                storage.saveToken(response.body()?.accessToken!!)
            }
            requestResultLiveData.postValue(response.isSuccessful)
        }
    }


}