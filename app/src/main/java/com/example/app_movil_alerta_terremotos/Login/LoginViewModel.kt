package com.example.app_movil_alerta_terremotos.Login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_movil_alerta_terremotos.Auth.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
        private val repository: AuthRepository = AuthRepository()
):ViewModel() {
        val currentUser = repository.currentUser
    val hasUser:Boolean
    get() = repository.hasUser()
    var loginUiState by mutableStateOf(LoginUiState())
        private set
    fun onUserNameChange(userName: String){
        loginUiState = loginUiState.copy(userName = userName)
    }
    fun onPasswordNameChange(password: String){
        loginUiState = loginUiState.copy(password = password)
    }
    fun onUserNameChangeSignup(userName: String){
        loginUiState = loginUiState.copy(userNameIniciado = userName)
    }
    fun onPasswordNameChangeSignup(password: String){
        loginUiState = loginUiState.copy(passwordIniciado = password)
    }
    fun onConfirmPasswordNameChange(password: String){
        loginUiState = loginUiState.copy(confirmarPasswordIniciado = password)
    }

    private fun validateLoginFrom() =
        loginUiState.userName.isNotBlank() &&
                loginUiState.password.isNotBlank()
    private fun validateSignupForm() =
        loginUiState.userNameIniciado.isNotBlank() &&
                loginUiState.passwordIniciado.isNotBlank() &&
                loginUiState.confirmarPasswordIniciado.isNotBlank()

    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateSignupForm()){
                throw IllegalArgumentException("El correo y contraseña no tienen datos!")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            if (loginUiState.passwordIniciado != loginUiState.confirmarPasswordIniciado){
                throw IllegalArgumentException("Las contraseñas no son iguales...")
            }
            loginUiState = loginUiState.copy(signUpError =  null)
            repository.createUser(
                loginUiState.userNameIniciado,
                loginUiState.passwordIniciado
            ) { isSuccessful ->
                if (isSuccessful){
                    Toast.makeText(
                        context, "Registro exitoso!",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                }else {
                    Toast.makeText(
                        context, "No se pudo Registrar!",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        } catch (e:Exception){
            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }
    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateLoginFrom()){
                throw IllegalArgumentException("El correo y contraseña no tienen datos!")
            }
            loginUiState = loginUiState.copy(isLoading = true)

            loginUiState = loginUiState.copy(loginError =  null)
            repository.login(
                loginUiState.userName,
                loginUiState.password
            ) { isSuccessful ->
                if (isSuccessful){
                    Toast.makeText(
                        context, "Inicio de sesión exitoso!",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)

                }else {
                    Toast.makeText(
                        context, "No se pudo iniciar la sesión!",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        } catch (e:Exception){
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }
}


data class LoginUiState(
    val userName: String = "",
    val password: String = "",
    val userNameIniciado: String = "",
    val passwordIniciado: String = "",
    val confirmarPasswordIniciado: String = "",
    val isLoading:Boolean = false,
    val isSuccessLogin:Boolean = false,
    val signUpError:String? = null,
    val loginError:String? = null

)