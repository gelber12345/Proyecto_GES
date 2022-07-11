package com.example.proyecto.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


sealed class AppScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object MapScreen : AppScreens("first_screen", "Mapa", Icons.Filled.Home)
    object DetalleScreen : AppScreens("detalle_screen", "Eventos", Icons.Filled.Home)
    object ComentarioScreen : AppScreens("comentario_screen", "Eventos", Icons.Filled.Home)
    object CuentaScreen : AppScreens("cuenta_screen", "Cuenta", Icons.Filled.AccountCircle)
    object CuentaEditScreen : AppScreens("cuenta_edit_screen", "Cuenta", Icons.Filled.AccountCircle)
}
