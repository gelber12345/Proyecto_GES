package com.example.proyecto.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyecto.Navigation.AppScreens
import com.example.proyecto.model.Data
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun RegisterScreen(navController: NavHostController) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = Data(context)
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var first_name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(88, 149, 238))
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Spacer(Modifier.height(40.0.dp))

            Text(
                text = "GAS.PE",
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Encuentra precios mas bajos",
                fontSize = 16.sp,
                color = Color(208, 227, 237)
            )

            Spacer(Modifier.height(48.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(Color(88, 149, 238)),
                shape = RoundedCornerShape(24.dp)
            )
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Crear una nueva cuenta",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(18.dp)
                    )

                    OutlinedTextField(
                        value = first_name,
                        onValueChange = { first_name = it },
                        label = { Text("Nombres", fontSize =16.sp) },
                        placeholder = {
                            Text(
                                "Ingrese su nombre",
                                fontSize = 16.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "firstNameIcon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Correo Electrónico", fontSize = 16.sp) },
                        placeholder = {
                            Text(
                                text = "Ingrese su correo electrónico",
                                fontSize = 16.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "emailIcon"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true
                    )

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña", fontSize = 16.sp) },
                        placeholder = {
                            Text(
                                text = "Ingrese su contraseña",
                                fontSize = 16.sp
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "passwordIcon"
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true
                    )

                    Spacer(Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (first_name == "" || email == "" || password == "") {
                                Toast.makeText(
                                    context,
                                    "Complete todos los campos.",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            } else {
                                val data = HashMap<String, String>()
                                data["correo"] = email
                                data["mantenimiento"] = ""
                                data["nombre"] = first_name
                                data["password"] = password
                                data["placa"] = ""
                                data["soat"] = ""


                                db.collection("usuarios")
                                    .add(data)
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            context,
                                            "Usuario registrado exitosamente",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        navController.navigate(AppScreens.LoginScreen.route)
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(
                                            context,
                                            "ERROR - El usuario no pudo ser registrado",
                                            Toast.LENGTH_LONG
                                        )
                                            .show()
                                    }
                            }
                        },
                        shape = RoundedCornerShape(20.dp),
                        elevation = ButtonDefaults.elevation(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 24.dp,
                                end = 24.dp
                            ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White,
                            backgroundColor = Color(88, 149, 238)
                        )
                    ) {
                        Text(
                            text = "Registrarse",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Row {
                        Text(
                            text = "¿Ya tienes una cuenta?", Modifier.padding(end = 8.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Iniciar Sesión", fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                navController.navigate(AppScreens.LoginScreen.route)
                            },
                            color = Color(88, 149, 238)
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}