package com.example.proyecto.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.proyecto.Screens.ComentScreen
import com.example.proyecto.Screens.DatosScreen
import com.example.proyecto.Screens.DetalleScreen
import com.example.proyecto.Screens.MapScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AppNavigation(
    navController: NavHostController
) {
    //val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MapScreen.route) {
        composable(
            route = AppScreens.MapScreen.route
        ) {
            MapScreen(navController)
        }
        composable(
            route = AppScreens.DetalleScreen.route + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })
        ) {
            DetalleScreen(navController, it.arguments?.getString("text"))
        }
        composable(
            route = AppScreens.ComentarioScreen.route + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })
        ) {
            ComentScreen(navController, it.arguments?.getString("text"))
        }
        composable(
            route = AppScreens.CuentaScreen.route
        ) {
            DatosScreen(navController)
        }
    }
}