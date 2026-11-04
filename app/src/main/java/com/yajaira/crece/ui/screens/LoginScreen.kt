package com.yajaira.crece.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yajaira.crece.ui.theme.MoradoOscuro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginExitoso: (String) -> Unit,
    onVolver: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Iniciar Sesión", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Text("←", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "¡BIENVENIDA DE VUELTA!",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.sp
            )
            Text(
                text = "Ingresa a tu cuenta",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MoradoOscuro
            )
            Text(
                text = "Accede a tus postulaciones, apoyos y cursos guardados.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campo Correo
            Text("Correo electrónico", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = correo,
                onValueChange = {
                    correo = it
                    errorMensaje = ""
                },
                placeholder = { Text("ejemplo@correo.com", fontSize = 13.sp) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Contraseña
            Text("Contraseña", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = contrasena,
                onValueChange = {
                    contrasena = it
                    errorMensaje = ""
                },
                placeholder = { Text("••••••••", fontSize = 13.sp) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            if (errorMensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = errorMensaje,
                    fontSize = 12.sp,
                    color = Color(0xFFB00020),
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    if (correo.isBlank() || contrasena.isBlank()) {
                        errorMensaje = "Ingresa tu correo y contraseña para continuar."
                    } else if (!correo.contains("@")) {
                        errorMensaje = "Ingresa un correo electrónico válido."
                    } else {
                        // Extrae el nombre antes del @ para saludarlo o asigna un nombre de usuario
                        val nombreUsuario = correo.substringBefore("@").replaceFirstChar { it.uppercase() }
                        onLoginExitoso(nombreUsuario)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Iniciar sesión",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}