package com.example.app_movil_alerta_terremotos


import androidx.compose.runtime.Composable
import com.example.app_movil_alerta_terremotos.Model.LocationViewModel


@Composable
fun FrmHome(NavegarMenu: () -> Unit){

    NavigationPage() {
        Rutas.login.ruta
    }

}