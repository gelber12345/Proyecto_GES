package com.example.proyecto

import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.proyecto.Navigation.AppNavigation
import com.example.proyecto.Navigation.AppScreens
import com.example.proyecto.Navigation.Login
import com.example.proyecto.Screens.Components.ButtomNavigationBar
import com.example.proyecto.Screens.LoginScreen
import com.example.proyecto.model.CommentGasolinera
import com.example.proyecto.model.CommentGasolinera.formatToInstant
import com.example.proyecto.model.MarkerMap
import com.example.proyecto.ui.theme.ProyectoTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    lateinit var auth: FirebaseAuth
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            ProyectoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    db.collection("gasolineras")
                        .get()
                        .addOnSuccessListener { result ->
                            for (register in result) {
                                var mark = MarkerMap.Mark(
                                    register.data["nombre"].toString(), register.data["latitud"].toString().toDouble(), register.data["longitud"].toString().toDouble(),
                                    register.data["distrito"].toString(), register.data["GLP"].toString().toDouble(),register.data["gas90"].toString().toDouble(),register.data["gas85"].toString().toDouble() ,register.data["direccion"].toString()
                                )
                                MarkerMap.add(mark)
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w(ContentValues.TAG, "Error getting Markers", exception)
                        }

                    db.collection("Comentarios")
                        .get()
                        .addOnSuccessListener { result ->
                            for (register in result) {
                                var coment = CommentGasolinera.Comment(
                                    register.data["Autor"].toString(),register.data["Description"].toString(),
                                    formatToInstant(register.data["fecha"].toString()),register.data["gasolinera"].toString()
                                    )
                                    CommentGasolinera.addComment(coment)

                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.w(ContentValues.TAG, "Error getting Markers", exception)
                        }

                    val navController = rememberNavController()
                    //AppNavigation(navController)
                    Login(navController)
                    //Main()
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Main() {
    val navController = rememberNavController() // Redirección de vistas
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
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProyectoTheme {
        Greeting("Android")
    }
}