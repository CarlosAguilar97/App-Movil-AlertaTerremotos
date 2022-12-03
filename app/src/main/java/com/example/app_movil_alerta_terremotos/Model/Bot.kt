package com.example.app_movil_alerta_terremotos.Model

data class Bot(
    var ID : String ? = null,
    var Estado: Boolean ? = null,

    ){
    init {

        this.ID = ID
        this.Estado = Estado
    }
}
