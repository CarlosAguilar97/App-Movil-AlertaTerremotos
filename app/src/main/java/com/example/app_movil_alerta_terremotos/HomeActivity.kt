package com.example.app_movil_alerta_terremotos


import androidx.compose.runtime.Composable


@Composable
fun FrmHome(NavegarMenu: () -> Unit){

    NavigationPage( NavegarLogin = {
        Rutas.login.ruta
    })

}