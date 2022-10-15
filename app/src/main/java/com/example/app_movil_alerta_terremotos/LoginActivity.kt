package com.example.app_movil_alerta_terremotos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.app_movil_alerta_terremotos.Login.LoginViewModel

@Composable
fun IconsExample() {
    Icon(Icons.Filled.Menu, "menu")   // ok
    Icon(Icons.Filled.Print, "print") // ok
}
@Composable 
fun FrmLogin(
    loginViewModel: LoginViewModel? = null,
    Navegarhome: () -> Unit,
    NavegarRegistro: () -> Unit,
    NavegarLogin:()-> Unit){
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current
   // val usuario = remember { mutableStateOf("") }
    //val contra = remember { mutableStateOf("") }
    var hidden = remember { mutableStateOf(false) }

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
                  onClick = { NavegarLogin()} ,shape = RoundedCornerShape(60)) {
                  Text(text = "  Iniciar  ")

              }
              Button( onClick = {   NavegarRegistro()
              }
                  ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                  ,shape = RoundedCornerShape(60)) {
                  Text(text = "Registrar",color = Color.White)
              }
          }
           Spacer(modifier = Modifier.padding(2.dp))
           Text(text = "Hola Bienvenido!")
           Spacer(modifier = Modifier.padding(2.dp))
           if (isError){
               Text(text = loginUiState?.loginError ?: "Error Desconocido",color = Color.Red)
           }
           Spacer(modifier = Modifier.padding(2.dp))
           OutlinedTextField( modifier = Modifier.fillMaxWidth(),
               value = loginUiState?.userName ?: "",
               onValueChange = {
                  loginViewModel?.onUserNameChange(it)
               },
               leadingIcon = {
                             Icon(imageVector = Icons.Default.Person, contentDescription = null)
               },
               label = {
                   Text(text = "Email")

               }
           )
           Spacer(modifier = Modifier.padding(2.dp))
           OutlinedTextField(modifier = Modifier.fillMaxWidth(),
               value = loginUiState?.password ?: "",
               onValueChange = { loginViewModel?.onPasswordNameChange(it) },
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
           Button(modifier = Modifier.fillMaxWidth(),
               onClick = {
                   loginViewModel?.loginUser(context)

               },shape = RoundedCornerShape(60)
           ) {
               Text(text = "Iniciar Sesión")
           }
           Spacer(modifier = Modifier.padding(2.dp))


               Text(
                   text = "Olvidate tu Contraseña?.",
                   color = Color.Red,
                   modifier = Modifier
                       .clickable(
                           onClick = {

                           }
                       )
               )


       }
       if(loginUiState?.isLoading == true){
           CircularProgressIndicator()
       }
       LaunchedEffect(key1 = loginViewModel?.hasUser){
           if (loginViewModel?.hasUser == true){
               Navegarhome()
           }
       }
   }


}
