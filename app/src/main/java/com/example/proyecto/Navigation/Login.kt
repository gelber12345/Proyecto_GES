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
fun Login(
    navController: NavHostController
) {
    //val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(
            route = AppScreens.RegisterScreen.route
        ) {
            RegisterScreen(navController)
        }
        composable(
            route = AppScreens.LoginScreen.route
        ) {
            LoginScreen(navController)
        }
        composable(
            route = AppScreens.VistaScreen.route
        ) {
            Vista()
        }
    }
}