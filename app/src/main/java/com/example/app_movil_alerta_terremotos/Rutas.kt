package com.example.app_movil_alerta_terremotos

sealed class Rutas(
    val ruta: String
){
    object  inicio: Rutas("Inicio")
    object  login: Rutas("Login")
    object  home: Rutas("Home")
    object  perfil: Rutas("Perfil")
    object  registro: Rutas("Registro")
    object  apoderado: Rutas("Apoderado")
    object  bot: Rutas("Bot")

}