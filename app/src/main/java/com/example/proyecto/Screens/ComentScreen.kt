package com.example.proyecto.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.Navigation.AppScreens
import com.example.proyecto.model.CommentGasolinera
import java.time.LocalDateTime


@Composable
fun ComentScreen(navController: NavController, text: String?){

    var comentario by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar() {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                })
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "COMENTARIOS DE LA GASOLINERA"
            )
        }
     },
    ) {
        Box (
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        )  {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(18, 114, 163))
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                Text("AÑADIR COMENTARIO",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center)

                Spacer(Modifier.height(60.dp))

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(Color(18, 114, 163)),

                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(
                        Modifier.background(Color(231, 235, 230)),
                        horizontalAlignment = Alignment.CenterHorizontally)

                    {

                        Text("Ingrese su comentario: ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Spacer(Modifier.height(20.dp))

                        OutlinedTextField(
                            value = comentario,
                            onValueChange = { comentario = it },
                            label = { Text("Correo Electrónico") },
                            placeholder = { Text(text = "Ingrese su correo electrónico") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )

                        Spacer(Modifier.height(16.dp))

                        Button(onClick = { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if (text != null) {
                                addComent(comentario,text)
                            }
                            navController.navigate(AppScreens.DetalleScreen.route + "/${text}")
                            navController.popBackStack()
                        }
                        }) {
                            Text(text = "Añadir Comentario")
                        }
                        Spacer(Modifier.height(16.dp))
                    }
                }
            }

        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
fun addComent(valor: String,gasolinera:String){
    if (valor!=""){
        CommentGasolinera.addComment(
            CommentGasolinera.Comment("Anonimo",valor,
                LocalDateTime.now(),gasolinera
            ))
    }
}