package com.yajaira.crece.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yajaira.crece.ui.theme.MoradoOscuro

data class NegocioItem(
    val id: String,
    val nombreNegocio: String,
    val emprendedora: String,
    val categoria: String,
    val municipio: String,
    val descripcion: String,
    val whatsapp: String,
    val emoji: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasaporteTalentoScreen(
    onVerOportunidades: () -> Unit,
    onVolverAInicio: () -> Unit
) {
    val context = LocalContext.current
    var textoBusqueda by remember { mutableStateOf("") }
    var mostrarModalAgregar by remember { mutableStateOf(false) }

    // Campos para agregar un nuevo negocio
    var nuevoNombreNegocio by remember { mutableStateOf("") }
    var nuevaEmprendedora by remember { mutableStateOf("") }
    var nuevaCategoria by remember { mutableStateOf("") }
    var nuevoMunicipio by remember { mutableStateOf("") }
    var nuevaDescripcion by remember { mutableStateOf("") }
    var nuevoWhatsapp by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    // Lista dinámica de negocios (empieza con los 4 iniciales y permite agregar más)
    val listaNegocios = remember {
        mutableStateListOf(
            NegocioItem(
                id = "1",
                nombreNegocio = "Confecciones Mari",
                emprendedora = "María Gutiérrez",
                categoria = "Textil y Costura",
                municipio = "León",
                descripcion = "Arreglos de ropa, vestidos sobre medida y uniformes escolares de excelente calidad.",
                whatsapp = "4771234567",
                emoji = "🧵"
            ),
            NegocioItem(
                id = "2",
                nombreNegocio = "Postres Doña Lupe",
                emprendedora = "Guadalupe Ramos",
                categoria = "Repostería y Comida",
                municipio = "Irapuato",
                descripcion = "Pasteles artesanales, galletas decoradas y gelatinas para eventos especiales.",
                whatsapp = "4629876543",
                emoji = "🎂"
            ),
            NegocioItem(
                id = "3",
                nombreNegocio = "Artesanías El Pincel",
                emprendedora = "Sofia Méndez",
                categoria = "Manualidades",
                municipio = "Celaya",
                descripcion = "Pintura en cerámica, recuerdos para bodas y bautizos hechos 100% a mano.",
                whatsapp = "4615551234",
                emoji = "🎨"
            ),
            NegocioItem(
                id = "4",
                nombreNegocio = "Joyería Fina Rosas",
                emprendedora = "Rosa María Cano",
                categoria = "Accesorios y Moda",
                municipio = "Guanajuato Capital",
                descripcion = "Bisutería fina en chapa de oro, pulseras personalizadas y accesorios artesanales.",
                whatsapp = "4734448899",
                emoji = "✨"
            )
        )
    }

    val negociosFiltrados = listaNegocios.filter {
        it.nombreNegocio.contains(textoBusqueda, ignoreCase = true) ||
                it.categoria.contains(textoBusqueda, ignoreCase = true) ||
                it.municipio.contains(textoBusqueda, ignoreCase = true) ||
                it.emprendedora.contains(textoBusqueda, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Escaparate de Negocios GTO", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onVolverAInicio) {
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
            Text(
                text = "TALENTO EMPRENDEDOR",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.sp
            )
            Text(
                text = "Consume local y apoya",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MoradoOscuro
            )
            Text(
                text = "Directorio de productos y servicios creados por emprendedoras de Guanajuato.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón destacado para Registrar Negocio Propio
            Button(
                onClick = { mostrarModalAgregar = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("➕ Registrar mi negocio aquí", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buscador
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                placeholder = { Text("Buscar producto, servicio o ciudad...", fontSize = 14.sp) },
                leadingIcon = { Text("🔍") },
                trailingIcon = {
                    if (textoBusqueda.isNotEmpty()) {
                        IconButton(onClick = { textoBusqueda = "" }) { Text("✕", fontSize = 14.sp) }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Negocios locales",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoradoOscuro
                )
                Text(
                    text = "${negociosFiltrados.size} activos",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Lista Dinámica de Negocios
            negociosFiltrados.forEach { negocio ->
                TarjetaNegocioItem(
                    negocio = negocio,
                    onContactarWhatsapp = {
                        val url = "https://api.whatsapp.com/send?phone=52${negocio.whatsapp}&text=Hola,%20vi%20tu%20negocio%20en%20Ella%20Crece%20GTO"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    // Modal para Registrar Nuevo Negocio
    if (mostrarModalAgregar) {
        AlertDialog(
            onDismissRequest = { mostrarModalAgregar = false },
            title = { Text("Registrar nuevo negocio", fontWeight = FontWeight.Bold, color = MoradoOscuro) },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Text("Ingresa los datos para publicar tu negocio en el escaparate:", fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = nuevoNombreNegocio,
                        onValueChange = { nuevoNombreNegocio = it; mensajeError = "" },
                        placeholder = { Text("Nombre de tu negocio *", fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = nuevaEmprendedora,
                        onValueChange = { nuevaEmprendedora = it; mensajeError = "" },
                        placeholder = { Text("Tu nombre completo *", fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = nuevaCategoria,
                        onValueChange = { nuevaCategoria = it; mensajeError = "" },
                        placeholder = { Text("Categoría (ej. Costura, Postres) *", fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = nuevoMunicipio,
                        onValueChange = { nuevoMunicipio = it; mensajeError = "" },
                        placeholder = { Text("Municipio (ej. León, Irapuato) *", fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = nuevoWhatsapp,
                        onValueChange = { nuevoWhatsapp = it; mensajeError = "" },
                        placeholder = { Text("WhatsApp (10 dígitos) *", fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = nuevaDescripcion,
                        onValueChange = { nuevaDescripcion = it; mensajeError = "" },
                        placeholder = { Text("Descripción corta de tus productos *", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )

                    if (mensajeError.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(mensajeError, fontSize = 11.sp, color = Color(0xFFB00020), fontWeight = FontWeight.Bold)
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (nuevoNombreNegocio.isBlank() || nuevaEmprendedora.isBlank() || nuevaCategoria.isBlank() || nuevoMunicipio.isBlank() || nuevoWhatsapp.isBlank() || nuevaDescripcion.isBlank()) {
                            mensajeError = "⚠️ Completa todos los campos para guardar."
                        } else {
                            listaNegocios.add(
                                0, // Se agrega al inicio de la lista
                                NegocioItem(
                                    id = System.currentTimeMillis().toString(),
                                    nombreNegocio = nuevoNombreNegocio,
                                    emprendedora = nuevaEmprendedora,
                                    categoria = nuevaCategoria,
                                    municipio = nuevoMunicipio,
                                    descripcion = nuevaDescripcion,
                                    whatsapp = nuevoWhatsapp,
                                    emoji = "🌟"
                                )
                            )
                            // Limpiar formulario y cerrar modal
                            nuevoNombreNegocio = ""
                            nuevaEmprendedora = ""
                            nuevaCategoria = ""
                            nuevoMunicipio = ""
                            nuevaDescripcion = ""
                            nuevoWhatsapp = ""
                            mostrarModalAgregar = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Guardar negocio", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarModalAgregar = false }) {
                    Text("Cancelar", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        )
    }
}

@Composable
private fun TarjetaNegocioItem(
    negocio: NegocioItem,
    onContactarWhatsapp: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(negocio.emoji, fontSize = 22.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = negocio.nombreNegocio, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
                    Text(text = "Por ${negocio.emprendedora} · ${negocio.municipio}", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Surface(shape = RoundedCornerShape(50), color = MaterialTheme.colorScheme.surfaceVariant) {
                Text(negocio.categoria, fontSize = 11.sp, color = MoradoOscuro, modifier = Modifier.padding(8.dp, 3.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = negocio.descripcion,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Botón de WhatsApp estilizado en verde morado
            Button(
                onClick = onContactarWhatsapp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("💬", fontSize = 16.sp, modifier = Modifier.padding(end = 8.dp))
                    Text(
                        text = "Conectar por WhatsApp",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}