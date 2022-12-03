package com.example.app_movil_alerta_terremotos.Model

data class Ubicacion(
    var Longitud: String ? =  null,
    var Latitud: String ? =  null

){
    init {
        this.Longitud = Longitud
        this.Latitud = Latitud
    }
}