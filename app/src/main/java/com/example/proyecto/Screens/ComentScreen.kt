package com.example.proyecto.Screens

import android.os.Build
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.Navigation.AppScreens
import com.example.proyecto.model.CommentGasolinera
import com.example.proyecto.model.CommentGasolinera.formatToString
import com.example.proyecto.model.Data
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ComentScreen(navController: NavController, text: String?){

    var comentario by remember { mutableStateOf("") }
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val context = LocalContext.current
    var autor by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val dataStore = Data(context)

    LaunchedEffect(scope) {
        dataStore.getName.collect { data ->
            autor = data
        }
    }


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
                            label = { Text("Comentario") },
                            placeholder = { Text(text = "Ingrese su comentario") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )

                        Spacer(Modifier.height(16.dp))


                        val data = HashMap<String, String>()
                        data["Autor"] = autor
                        data["Description"] = comentario
                        data["fecha"] = LocalDateTime.now().toString()
                        data["gasolinera"] = text!!



                        Button(onClick = { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                                db.collection("Comentarios")
                                    .add(data)
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            context,
                                            "Comentario Registrado",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(
                                            context,
                                            "ERROR - El Comentario no pudo ser registrado",
                                            Toast.LENGTH_LONG
                                        )
                                            .show()
                                    }
                                addComent(comentario,text,autor)

                            navController.navigate(AppScreens.DetalleScreen.route + "/${text}")

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
fun addComent(valor: String,gasolinera:String,autor: String){
    if (valor!=""){
        CommentGasolinera.addComment(
            CommentGasolinera.Comment(autor,valor,
                LocalDateTime.now(),gasolinera
            ))
    }
}