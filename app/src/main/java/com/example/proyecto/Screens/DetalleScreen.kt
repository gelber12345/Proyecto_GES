package com.example.proyecto.Screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.Navigation.AppScreens
import com.example.proyecto.model.CommentGasolinera
import com.example.proyecto.model.MarkerMap
import com.google.maps.android.compose.Marker
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun DetalleScreen(navController: NavController, text: String?) {
    val aux by remember { mutableStateOf(text) }
    val gaso= MarkerMap.getMark(aux!!)
    val list : MutableList<CommentGasolinera.Comment> =  CommentGasolinera.getComments(aux!!)

    Scaffold(topBar = {
        TopAppBar() {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                })
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "DETALLES DE LA GASOLINERA"
            )
        }
    },
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 16.dp)
            .border(5.dp, color = Color(2, 136, 209), shape = RoundedCornerShape(8))
            .background(Color(18, 114, 163), shape = RoundedCornerShape(8))
            .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            Text("Gasolinera : ${gaso.nombre}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Justify)

            Spacer(Modifier.height(10.dp))
            Text("PRECIOS REGISTRADOS : ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Justify)

            Spacer(Modifier.height(10.dp))

            Text("GLP : ${gaso.GLP}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Justify)

            Spacer(Modifier.height(10.dp))
            Text("90 : ${gaso.gas90}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Justify)

            Spacer(Modifier.height(10.dp))
            Text("85 : ${gaso.gas85}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Justify)

            Spacer(Modifier.height(10.dp))

            Row() {
                Button(onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        navController.navigate(AppScreens.ComentarioScreen.route + "/${text}")
                    }
                }) {
                    Text(text = "Añadir Comentario")
                }
            }
            Text("Comentarios :",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Justify)


            Spacer(Modifier.height(16.dp))

            LazyColumn() {
                text?.let {
                    items(list) { dato ->
                        Card ( shape = RoundedCornerShape(15.dp),
                            backgroundColor = Color(240, 240, 240),
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .padding(2.dp),
                            elevation = 5.dp){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                Text(
                                    text = "Fecha : " + dato.fecha.dayOfMonth.toString() + "/" + dato.fecha.month.toString() + "/" + dato.fecha.year.toString() + "\n" +
                                            "Autor : " + dato.Autor + "\n" +
                                            "Descripcion:  " + dato.Description,
                                    Modifier.padding(start = 4.dp, end = 4.dp)

                                )
                            }
                        }
                        Spacer(Modifier.height(10.0.dp))
                    }
                }
            }
        }
    }


}

