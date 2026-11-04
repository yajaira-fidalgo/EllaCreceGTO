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
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yajaira.crece.ui.theme.MoradoOscuro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(
    onRegistroCompletado: (String) -> Unit,
    onIrALogin: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var municipio by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var aceptoTerminos by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf("") }
    var cargando by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Cuenta", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onIrALogin) {
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
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "ÚNETE A ELLA CRECE GTO",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.sp
            )
            Text(
                text = "Registra tus datos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MoradoOscuro
            )
            Text(
                text = "Todos los campos son obligatorios para validar tu Pasaporte de Talento.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Campo Nombre
            Text("Nombre completo *", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it; mensajeError = "" },
                placeholder = { Text("Ej. Ana María Torres", fontSize = 13.sp) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Campo Correo
            Text("Correo electrónico *", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it; mensajeError = "" },
                placeholder = { Text("ejemplo@correo.com", fontSize = 13.sp) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Campo Teléfono
            Text("Teléfono de contacto *", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it; mensajeError = "" },
                placeholder = { Text("10 dígitos", fontSize = 13.sp) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Campo Municipio
            Text("Municipio de Guanajuato *", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = municipio,
                onValueChange = { municipio = it; mensajeError = "" },
                placeholder = { Text("Ej. León, Irapuato, Celaya", fontSize = 13.sp) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Campo Contraseña
            Text("Contraseña *", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it; mensajeError = "" },
                placeholder = { Text("Mínimo 6 caracteres", fontSize = 13.sp) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Checkbox Términos y Condiciones
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = aceptoTerminos,
                    onCheckedChange = { aceptoTerminos = it; mensajeError = "" },
                    colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
                )
                Text(
                    text = "Acepto los términos, condiciones y aviso de privacidad de Ella Crece GTO.",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (mensajeError.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = mensajeError,
                    fontSize = 12.sp,
                    color = Color(0xFFB00020),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón Crear Cuenta con Conexión a Firestore
            Button(
                onClick = {
                    if (nombre.isBlank() || correo.isBlank() || telefono.isBlank() || municipio.isBlank() || contrasena.isBlank()) {
                        mensajeError = "⚠️ Debes llenar todos los campos obligatorios."
                    } else if (!aceptoTerminos) {
                        mensajeError = "⚠️ Debes aceptar los términos y condiciones para registrarte."
                    } else {
                        cargando = true
                        mensajeError = ""

                        // Instancia de Firestore
                        val db = Firebase.firestore

                        // Mapa con los datos de la usuaria
                        val nuevoUsuario = hashMapOf(
                            "nombre" to nombre.trim(),
                            "correo" to correo.trim(),
                            "telefono" to telefono.trim(),
                            "municipio" to municipio.trim(),
                            "fechaRegistro" to Timestamp.now()
                        )

                        // Guardar en la colección "usuarios" en Firestore
                        db.collection("usuarios")
                            .add(nuevoUsuario)
                            .addOnSuccessListener {
                                cargando = false
                                onRegistroCompletado(nombre)
                            }
                            .addOnFailureListener { e ->
                                cargando = false
                                mensajeError = "⚠️ Error al guardar en la nube: ${e.localizedMessage}"
                            }
                    }
                },
                enabled = !cargando,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                if (cargando) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Crear mi cuenta", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = onIrALogin,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("¿Ya tienes cuenta? Inicia sesión aquí", color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}