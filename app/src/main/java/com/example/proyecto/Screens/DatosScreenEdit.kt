package com.example.proyecto.Screens

import android.os.Build
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.Navigation.AppScreens
import com.example.proyecto.R
import com.example.proyecto.Screens.Components.DateFormater
import com.example.proyecto.Screens.Components.DatePickerview
import com.example.proyecto.model.CommentGasolinera
import com.example.proyecto.model.Data
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatosScreenEdit(navController: NavController){

    var placa by remember { mutableStateOf("") }
    var soat by remember { mutableStateOf("") }
    var fechaM by remember { mutableStateOf("12/10/21") }
    var datePicked:String? by remember { mutableStateOf(null) }
    var autor by remember { mutableStateOf("") }
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = Data(context)
    LaunchedEffect(scope) {
        dataStore.getSesion.collect { data ->
            autor = data
        }
    }
    LaunchedEffect(scope) {
        dataStore.getPlaca.collect { data ->
            placa = data
        }
    }
    LaunchedEffect(scope) {
        dataStore.getSoat.collect { data ->
            soat = data
        }
    }
    LaunchedEffect(scope) {
        dataStore.getDate.collect { data ->
            fechaM = data
        }
    }
    val updatedDate = { date : String ->
        datePicked = date
    }

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

                        OutlinedTextField(
                            value = placa,
                            onValueChange = { placa = it },
                            label = { Text("Placa") },
                            placeholder = { Text(text = "$placa") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
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
                        Text("SOAT : ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        OutlinedTextField(
                            value = soat,
                            onValueChange = { soat = it },
                            label = { Text("SOAT") },
                            placeholder = { Text(text = "$soat") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
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
                        Text("Ultimo mantenimiento :  ${ if( datePicked!=null) datePicked else fechaM }",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )


                    }
                    Spacer(Modifier.height(8.dp))
                    Row() {

                        DatePickerview(updatedDate)

                        Spacer(Modifier.width(10.dp))
                        Button(onClick = {
                            var local = LocalDateTime.now()
                            datePicked = "${local.dayOfMonth}/${local.monthValue}/${local.year}"

                        }) {
                            Text(text = "Ahora")
                        }

                    }


                    Spacer(Modifier.height(16.dp))

                    Button(onClick = {
                        fechaM = datePicked.toString()
                        val data = HashMap<String, String>()
                        data["soat"] = soat
                        data["mantenimiento"] = fechaM
                        data["placa"] = placa
                        Log.d("DATA " , "${autor}")
                        db.collection("usuarios").document(autor)
                            .set(data, SetOptions.merge())
                            .addOnSuccessListener {
                                Toast.makeText(
                                    context,
                                    "Guardado",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            .addOnFailureListener {
                            }
                        scope.launch {
                            datePicked?.let { dataStore.saveDate(it) }
                            dataStore.savePlaca(placa)
                            dataStore.saveSoat(soat)
                        }

                        navController.navigate(AppScreens.CuentaScreen.route )


                    }) {
                        Text(text = "EDITAR")
                    }

                    Spacer(Modifier.height(16.dp))
                }
            }
        }

    }
}