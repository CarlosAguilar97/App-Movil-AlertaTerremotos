package com.example.app_movil_alerta_terremotos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.app_movil_alerta_terremotos.Login.LoginViewModel

@Composable
fun FrmRegistro(
    loginViewModel: LoginViewModel? = null,
    Navegarhome:() -> Unit,
    NavegarRegistro:()->Unit,
    NavegarLogin:() -> Unit) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.signUpError != null
    val context = LocalContext.current
    val usuario = remember { mutableStateOf("") }
    val contra1 = remember { mutableStateOf("") }
    val contra2 = remember { mutableStateOf("") }
    var hidden = remember { mutableStateOf(false) } //1
    Box() {
        Image(
            painter = painterResource(id = R.drawable.backdatos),
            contentDescription = null, modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(

            painter = painterResource(id = R.drawable.logo),
            contentDescription = "", modifier = Modifier.size(150.dp)
        )
    }
    Box(
        Modifier
            .padding(50.dp)
            .fillMaxWidth()
            .padding(top = 190.dp)
    ) {


        Column(
            Modifier.fillMaxSize()
            ,verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .height(39.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(60))
            ) {
                Button(
                    onClick = { NavegarLogin() }
                    ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray) ,shape = RoundedCornerShape(60)) {
                    Text(text = "  Iniciar  ")

                }
                Button( onClick = { NavegarRegistro() }

                    ,shape = RoundedCornerShape(60)) {
                    Text(text = "Registrar",color = Color.White)
                }
            }
            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = "Hola Bienvenido!")
            Spacer(modifier = Modifier.padding(2.dp))
            if (isError){
                Text(text = loginUiState?.signUpError ?: "Error Desconocido",color = Color.Red)
            }
            Spacer(modifier = Modifier.padding(2.dp))
            OutlinedTextField( modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
                value = loginUiState?.userNameIniciado ?: "",
                onValueChange = {
                    loginViewModel?.onUserNameChangeSignup(it)
                },
                leadingIcon = {
                              Icon(imageVector = Icons.Default.Person,
                                  contentDescription = null)
                },
                label = {
                    Text(text = "Email")

                }
            )
            Spacer(modifier = Modifier.padding(2.dp))
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
                value = loginUiState?.passwordIniciado ?: "",
                onValueChange = { loginViewModel?.onPasswordNameChangeSignup(it) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                    )
                },
                label = { Text("Contraseña") },
                singleLine = true,
                placeholder = { Text("Contraseña") },
                visualTransformation = if (hidden.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (hidden.value)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Localized description for accessibility services
                    val description = if (hidden.value) "Hide password" else "Show password"

                    // Toggle button to hide or display password
                    IconButton(onClick = {hidden.value = !hidden.value}){
                        Icon(imageVector  = image, description)
                    }
                }
            )
            Spacer(modifier = Modifier.padding(2.dp))
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

                value = loginUiState?.confirmarPasswordIniciado ?: "",
                onValueChange = { loginViewModel?.onConfirmPasswordNameChange(it) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                    )
                },
                label = { Text("Confirmar-Contraseña") },
                singleLine = true,
                placeholder = { Text("Co-Contraseña") },
                visualTransformation = if (hidden.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (hidden.value)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Localized description for accessibility services
                    val description = if (hidden.value) "Hide password" else "Show password"

                    // Toggle button to hide or display password
                    IconButton(onClick = {hidden.value = !hidden.value}){
                        Icon(imageVector  = image, description)
                    }
                }
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                  loginViewModel?.createUser(context)
                },shape = RoundedCornerShape(60)
            ) {
                Text(text = "Registrarme")
            }
            Spacer(modifier = Modifier.padding(2.dp))




        }
    }

}