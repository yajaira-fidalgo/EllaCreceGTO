package com.yajaira.crece.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yajaira.crece.ui.theme.MoradoOscuro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesAccesibilidadScreen(
    onVolver: () -> Unit,
    onCerrarSesion: () -> Unit = {}
) {
    // Estados dinámicos de accesibilidad
    var textoGrande by remember { mutableStateOf(false) }
    var altoContraste by remember { mutableStateOf(false) }
    var lecturaVoz by remember { mutableStateOf(false) }

    // Modales interactivos para Mi Cuenta
    var mostrarModalEditarDatos by remember { mutableStateOf(false) }
    var mostrarModalNotificaciones by remember { mutableStateOf(false) }
    var mostrarDialogoCerrarSesion by remember { mutableStateOf(false) }

    // Configuración dinámica de tamaño de texto
    val tamañoTitulo = if (textoGrande) 22.sp else 18.sp
    val tamañoSubtitulo = if (textoGrande) 16.sp else 14.sp
    val tamañoTexto = if (textoGrande) 14.sp else 12.sp

    // Configuración dinámica de colores de Alto Contraste
    val colorFondoTarjeta = if (altoContraste) Color(0xFF1C1B1F) else MaterialTheme.colorScheme.surface
    val colorTextoPrincipal = if (altoContraste) Color.White else MoradoOscuro
    val colorTextoSecundario = if (altoContraste) Color(0xFFE6E1E5) else MaterialTheme.colorScheme.onSurfaceVariant

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil y Ajustes", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // Tarjeta de Perfil
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = colorFondoTarjeta),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.size(60.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("👩🏻‍💼", fontSize = 32.sp)
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Mariana López",
                            fontSize = tamañoTitulo,
                            fontWeight = FontWeight.Bold,
                            color = colorTextoPrincipal
                        )
                        Text(
                            text = "📍 León, Guanajuato",
                            fontSize = tamañoTexto,
                            color = colorTextoSecundario
                        )
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.padding(top = 6.dp)
                        ) {
                            Text(
                                text = "Pasaporte de Talento Activo",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = MoradoOscuro,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Banner informativo si activa Lectura por Voz
            if (lecturaVoz) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2FE))
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("🔊", fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
                        Text(
                            text = "Asistente de voz activo: Se leerán los textos al seleccionar elementos.",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = MoradoOscuro
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Sección: Accesibilidad e Inclusión
            Text(
                text = "ACCESIBILIDAD E INCLUSIÓN",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colorFondoTarjeta)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Opción 1: Texto Grande
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Texto más grande", fontSize = tamañoSubtitulo, fontWeight = FontWeight.Bold, color = colorTextoPrincipal)
                            Text("Aumenta el tamaño de la letra en toda la app.", fontSize = tamañoTexto, color = colorTextoSecundario)
                        }
                        Switch(
                            checked = textoGrande,
                            onCheckedChange = { textoGrande = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = MaterialTheme.colorScheme.primary)
                        )
                    }

                    Divider(modifier = Modifier.padding(vertical = 12.dp), color = MaterialTheme.colorScheme.surfaceVariant)

                    // Opción 2: Alto Contraste
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Alto contraste", fontSize = tamañoSubtitulo, fontWeight = FontWeight.Bold, color = colorTextoPrincipal)
                            Text("Cambia el fondo de las tarjetas para facilitar la lectura.", fontSize = tamañoTexto, color = colorTextoSecundario)
                        }
                        Switch(
                            checked = altoContraste,
                            onCheckedChange = { altoContraste = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = MaterialTheme.colorScheme.primary)
                        )
                    }

                    Divider(modifier = Modifier.padding(vertical = 12.dp), color = MaterialTheme.colorScheme.surfaceVariant)

                    // Opción 3: Lectura por Voz
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Lectura por voz asistida", fontSize = tamañoSubtitulo, fontWeight = FontWeight.Bold, color = colorTextoPrincipal)
                            Text("Escucha las descripciones de las vacantes y cursos.", fontSize = tamañoTexto, color = colorTextoSecundario)
                        }
                        Switch(
                            checked = lecturaVoz,
                            onCheckedChange = { lecturaVoz = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = MaterialTheme.colorScheme.primary)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Sección: Mi Cuenta
            Text(
                text = "MI CUENTA",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colorFondoTarjeta)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    // Botón Editar Datos y Pasaporte
                    TextButton(
                        onClick = { mostrarModalEditarDatos = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("📄 Editar mis datos y Pasaporte", color = colorTextoPrincipal, fontSize = tamañoSubtitulo)
                            Text("›", color = colorTextoPrincipal, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Divider(color = MaterialTheme.colorScheme.surfaceVariant)

                    // Botón Notificaciones y Avisos
                    TextButton(
                        onClick = { mostrarModalNotificaciones = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("🔔 Notificaciones y avisos", color = colorTextoPrincipal, fontSize = tamañoSubtitulo)
                            Text("›", color = colorTextoPrincipal, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón Cerrar Sesión
            OutlinedButton(
                onClick = { mostrarDialogoCerrarSesion = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFB00020))
            ) {
                Text("Cerrar sesión", fontSize = tamañoSubtitulo, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }

    // Modal 1: Editar mis datos y Pasaporte
    if (mostrarModalEditarDatos) {
        AlertDialog(
            onDismissRequest = { mostrarModalEditarDatos = false },
            title = { Text("📄 Pasaporte de Talento", fontWeight = FontWeight.Bold, color = MoradoOscuro) },
            text = {
                Column {
                    Text("Datos registrados de tu currículum digital:", fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("• Nombre: Mariana López", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Text("• Ubicación: León, Guanajuato", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Text("• Oficio principal: Confección y Costura", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Text("• Nivel de estudios: Bachillerato / IECA Taller", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Tus datos están actualizados y sincronizados con las vacantes del estado.", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            },
            confirmButton = {
                Button(
                    onClick = { mostrarModalEditarDatos = false },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Guardar cambios", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarModalEditarDatos = false }) {
                    Text("Cerrar", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        )
    }

    // Modal 2: Notificaciones y avisos
    if (mostrarModalNotificaciones) {
        AlertDialog(
            onDismissRequest = { mostrarModalNotificaciones = false },
            title = { Text("🔔 Notificaciones y Avisos", fontWeight = FontWeight.Bold, color = MoradoOscuro) },
            text = {
                Column {
                    Text("Configuración de alertas activas:", fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("✅ Alertas de nuevas vacantes en León", fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("✅ Recordatorios de citas de entrevista", fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("✅ Avisos de apertura de becas y apoyos GTO", fontSize = 13.sp)
                }
            },
            confirmButton = {
                Button(
                    onClick = { mostrarModalNotificaciones = false },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Entendido", color = Color.White)
                }
            }
        )
    }

    // Modal 3: Confirmación de Cerrar Sesión
    if (mostrarDialogoCerrarSesion) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoCerrarSesion = false },
            title = { Text("Cerrar sesión", fontWeight = FontWeight.Bold, color = MoradoOscuro) },
            text = { Text("¿Estás segura de que deseas salir de Ella Crece GTO?", fontSize = 13.sp) },
            confirmButton = {
                Button(
                    onClick = {
                        mostrarDialogoCerrarSesion = false
                        onCerrarSesion()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Sí, salir", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoCerrarSesion = false }) {
                    Text("Cancelar", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        )
    }
}