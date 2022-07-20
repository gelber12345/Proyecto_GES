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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.navigation.compose.rememberNavController
import com.example.proyecto.Navigation.AppNavigation
import com.example.proyecto.Navigation.AppScreens
import com.example.proyecto.Screens.Components.ButtomNavigationBar
import com.example.proyecto.model.Data
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
@Composable
fun LoginScreen(navController: NavHostController) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = Data(context)

    LaunchedEffect(scope) {
        dataStore.getEmail.collect { data ->
            email = data
        }
    }
    LaunchedEffect(scope) {
        dataStore.getPassword.collect { data ->
            password = data
        }
    }

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
                text = "Encuentra el mejor precio",
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
                        text = "Bienvenido",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(18.dp)
                    )

                    OutlinedTextField(
                        value = email,

                        onValueChange = { email = it },
                        label = { Text("Correo Electrónico") },
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
                        label = { Text("Contraseña") },
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
                            db.collection("usuarios")
                                .whereEqualTo("correo", email)
                                .whereEqualTo("password", password).get()
                                .addOnSuccessListener { users ->
                                    if (users.size() != 0) {
                                        Toast.makeText(
                                            context,
                                            "Bienvenido",
                                            Toast.LENGTH_LONG
                                        )
                                            .show()
                                        for (register in users) {
                                            scope.launch {
                                                dataStore.saveName(register.data["nombre"].toString())
                                                dataStore.saveSoat(register.data["soat"].toString())
                                                dataStore.savePlaca(register.data["placa"].toString())
                                                dataStore.saveDate(register.data["mantenimiento"].toString())
                                                dataStore.saveSesion(register.data["correo"].toString())
                                                dataStore.savePassword(register.data["password"].toString())
                                                dataStore.saveEmail(register.data["correo"].toString())
                                            }
                                        }
                                        navController.navigate(AppScreens.VistaScreen.route)
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "El usuario no se encuentra registrado.",
                                            Toast.LENGTH_LONG
                                        )
                                            .show()
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    Toast.makeText(
                                        context,
                                        "Error - ${exception}",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
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
                            text = "Iniciar Sesión",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = {

                            scope.launch {
                                dataStore.saveName("Anonimo")
                                dataStore.saveSesion("example@hotmail.com")
                            }
                            navController.navigate(AppScreens.VistaScreen.route)
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
                            text = "Entrar Sin Registro",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Row {
                        Text(
                            text = "¿Nuevo en GAS.PE?", Modifier.padding(end = 8.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Crear una cuenta", fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                navController.navigate(AppScreens.RegisterScreen.route)
                            },
                            color = Color(88, 149, 238)
                        )
                    }

                }
            }
        }
    }
}

