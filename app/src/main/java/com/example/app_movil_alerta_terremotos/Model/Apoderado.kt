package com.example.app_movil_alerta_terremotos.Model
data class Apoderado(
    var Nombre : String ? = null,
    var Celular : String ? = null,
    var ID : String ? = null,
    var Parentesco: String ? =  null,
    var Correo: String ? =  null,
    var Ida: String ? =  null

    ){
    init {
        this.Nombre = Nombre
        this.Celular = Celular
        this.ID = ID
        this.Parentesco = Parentesco
        this.Correo = Correo
        this.Ida = Ida
    }
}