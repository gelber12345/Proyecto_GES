package com.example.proyecto.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.proyecto.Screens.*

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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ComentScreen(navController, it.arguments?.getString("text"))
            }
        }
        composable(
            route = AppScreens.CuentaScreen.route
        ) {
            DatosScreen(navController)
        }
        composable(
            route = AppScreens.CuentaEditScreen.route
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DatosScreenEdit(navController)
            }
        }
        composable(
            route = AppScreens.VistaScreen.route
        ) {
            Vista()
        }
    }
}