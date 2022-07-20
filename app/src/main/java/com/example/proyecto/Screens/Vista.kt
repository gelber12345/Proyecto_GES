package com.example.proyecto.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyecto.Navigation.AppNavigation
import com.example.proyecto.Navigation.AppScreens
import com.example.proyecto.Screens.Components.ButtomNavigationBar

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Vista() {

    val navController = rememberNavController()
    //BOTTOM NAVIGATION
    val navigationItems = listOf(
        AppScreens.MapScreen,
        AppScreens.CuentaScreen

    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        bottomBar = { ButtomNavigationBar(navController = navController, items = navigationItems) }
    ) { innerPadding ->
        // Apply the padding globally to the whole BottomNavScreensController
        Surface(modifier = Modifier.padding(innerPadding)) {
            AppNavigation(navController)
        }
    }
}