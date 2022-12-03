package com.example.app_movil_alerta_terremotos.Model

data class Persona(
    var Nombre : String ? = null,
    var Celular : String ? = null,
    var ID : String ? = null,
    var Ciudad: String ? = null,
    var Idoc: String ? = null,

){
    init {
        this.Nombre = Nombre
        this.Celular = Celular
        this.ID = ID
        this.Ciudad  = Ciudad
        this.Idoc = Idoc
    }
}


