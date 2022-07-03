package com.example.proyecto.Screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.R

@Composable
fun DatosScreen(navController: NavController){

    val placa by remember { mutableStateOf("7SD AS2")}
    val soat by remember { mutableStateOf("SOAT")}
    val fechaM by remember { mutableStateOf("12/10/21")}

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

            Text("DATOS PERSONALES",
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

                    Row() {
                        Icon(
                            painterResource(id = R.drawable.placa ),
                            contentDescription = "Placa",
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(Modifier.width(10.dp))
                        Text("Placa : $placa",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(Modifier.height(16.dp))

                    Row() {
                        Icon(
                            painterResource(id = R.drawable.proteger ),
                            contentDescription = "SOAT",
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(Modifier.width(10.dp))
                        Text("SOAT : $soat",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(Modifier.height(16.dp))

                    Row() {
                        Icon(
                            painterResource(id = R.drawable.mantenimiento ),
                            contentDescription = "Mantenimiento",
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(Modifier.width(10.dp))
                        Text("Ultimo mantenimiento : $fechaM",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(Modifier.height(16.dp))


                }
            }
        }

    }
}