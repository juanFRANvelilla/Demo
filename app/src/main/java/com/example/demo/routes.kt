package com.example.demo

sealed class Routes(val route: String){
    object PantallaFavoritos: Routes("favoritos")
    object PantallaConfiguracion: Routes("configuraciones")
}