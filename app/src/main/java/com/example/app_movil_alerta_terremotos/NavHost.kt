package com.example.app_movil_alerta_terremotos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_movil_alerta_terremotos.Login.LoginViewModel
import com.example.app_movil_alerta_terremotos.Model.LocationDetails
import com.example.app_movil_alerta_terremotos.Model.LocationViewModel

@Composable
fun NavegacionHost(loginViewModel: LoginViewModel){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.inicio.ruta,
    ) {
        composable(Rutas.inicio.ruta){
            FrmInicio (
               NavegarLogin = {
                    navController.navigate(Rutas.login.ruta)
                },
                NavegarRegistro = {
                    navController.navigate(Rutas.registro.ruta)
                }
            )
        }
        composable(Rutas.login.ruta){
            FrmLogin (
                Navegarhome = {
                    navController.navigate(Rutas.home.ruta)

                },
                NavegarRegistro = {
                    navController.navigate(Rutas.registro.ruta)
                },
                NavegarLogin = {
                    navController.navigate(Rutas.login.ruta)
                },
                loginViewModel = loginViewModel
            )
        }
        composable(Rutas.home.ruta){
            FrmHome (
                NavegarMenu = {
                    navController.navigate(Rutas.home.ruta)
                }
            )
        }
        composable(Rutas.registro.ruta){
            FrmRegistro(
                NavegarRegistro = {
                    navController.navigate(Rutas.registro.ruta)
                } ,
                NavegarLogin = {
                    navController.navigate(Rutas.login.ruta)
                },
                        Navegarhome = {
                    navController.navigate(Rutas.home.ruta)
                },
                loginViewModel = loginViewModel
            )
        }
    }
}

