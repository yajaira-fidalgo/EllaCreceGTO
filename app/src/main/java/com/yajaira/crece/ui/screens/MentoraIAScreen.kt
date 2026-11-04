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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yajaira.crece.ui.theme.MoradoOscuro

data class CursoItem(
    val id: String,
    val titulo: String,
    val institucion: String,
    val duracion: String,
    val modalidad: String,
    val emoji: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MentoraIAScreen(
    onVolver: () -> Unit
) {
    var textoBusqueda by remember { mutableStateOf("") }

    // Lista de IDs de los cursos donde la usuaria ya se inscribió
    val cursosInscritosIds = remember { mutableStateListOf<String>() }

    // Estados para la inscripción
    var cursoSeleccionadoParaInscripcion by remember { mutableStateOf<CursoItem?>(null) }
    var correoUsuario by remember { mutableStateOf("") }
    var mostrarConfirmacionFinal by remember { mutableStateOf(false) }

    // Estados para la Mentora IA
    var preguntaIA by remember { mutableStateOf("") }
    var respuestaIA by remember { mutableStateOf("¡Hola! Soy tu Mentora IA. ¿En qué puedo orientarte hoy sobre cursos, becas o tu negocio?") }

    val listaCursos = listOf(
        CursoItem(
            id = "1",
            titulo = "Patronaje y Corte Textil Básico",
            institucion = "IECA Guanajuato",
            duracion = "20 horas · Gratuito",
            modalidad = "Presencial / Taller León",
            emoji = "✂️"
        ),
        CursoItem(
            id = "2",
            titulo = "Finanzas y Costos para tu Negocio",
            institucion = "Ella Crece GTO",
            duracion = "5 módulos · En línea",
            modalidad = "A tu ritmo",
            emoji = "📊"
        ),
        CursoItem(
            id = "3",
            titulo = "Ventas por WhatsApp y Redes Sociales",
            institucion = "Capacitación Mujer GTO",
            duracion = "10 horas · Virtual",
            modalidad = "Clases grabadas",
            emoji = "📱"
        )
    )

    val cursosFiltrados = listaCursos.filter {
        it.titulo.contains(textoBusqueda, ignoreCase = true) ||
                it.institucion.contains(textoBusqueda, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Capacitación y Cursos", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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
            Text(
                text = "CRECE A TU RITMO",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.sp
            )
            Text(
                text = "Aprende habilidades nuevas",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MoradoOscuro
            )
            Text(
                text = "Cursos gratuitos y talleres prácticos para impulsar tu perfil profesional o emprendimiento.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Módulo Interactivo de Mentora IA
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("✨", fontSize = 24.sp, modifier = Modifier.padding(end = 8.dp))
                        Text(
                            text = "Mentora IA Crece",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = MoradoOscuro
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = respuestaIA,
                            fontSize = 12.sp,
                            color = MoradoOscuro,
                            modifier = Modifier.padding(12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = preguntaIA,
                            onValueChange = { preguntaIA = it },
                            placeholder = { Text("Hazle una pregunta a la Mentora...", fontSize = 12.sp) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedContainerColor = MaterialTheme.colorScheme.surface
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                if (preguntaIA.isNotEmpty()) {
                                    respuestaIA = "Analizando tu consulta sobre '${preguntaIA}'...\n\nTe recomiendo inscribirte al taller de 'Finanzas y Costos' o acudir al centro IECA León para capacitación presencial gratuita."
                                    preguntaIA = ""
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Enviar", fontSize = 12.sp, color = Color.White)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buscador
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                placeholder = { Text("Buscar curso, oficio o tema...", fontSize = 14.sp) },
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
                    text = "Cursos disponibles",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoradoOscuro
                )
                Text(
                    text = "${cursosFiltrados.size} talleres",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Lista de Cursos
            cursosFiltrados.forEach { curso ->
                TarjetaCursoItem(
                    curso = curso,
                    estaInscrito = cursosInscritosIds.contains(curso.id),
                    onInscribirse = {
                        cursoSeleccionadoParaInscripcion = curso
                        mostrarConfirmacionFinal = false
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    // Ventana Flotante de Inscripción
    cursoSeleccionadoParaInscripcion?.let { curso ->
        AlertDialog(
            onDismissRequest = { cursoSeleccionadoParaInscripcion = null },
            title = {
                Text(
                    text = if (!mostrarConfirmacionFinal) "Inscripción al curso" else "📩 ¡Inscripción enviada!",
                    fontWeight = FontWeight.Bold,
                    color = MoradoOscuro
                )
            },
            text = {
                if (!mostrarConfirmacionFinal) {
                    Column {
                        Text(
                            text = "Estás a un paso de inscribirte a '${curso.titulo}'. Ingresa tu correo para enviarte el temario:",
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedTextField(
                            value = correoUsuario,
                            onValueChange = { correoUsuario = it },
                            placeholder = { Text("ejemplo@correo.com", fontSize = 13.sp) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                    }
                } else {
                    val correoFinal = if (correoUsuario.isNotBlank()) correoUsuario else "usuario@ejemplo.com"
                    Text(
                        text = "Se ha enviado el temario, horarios y accesos del taller '${curso.titulo}' a:\n\n$correoFinal\n\n¡Nos vemos en la clase!",
                        fontSize = 13.sp
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (!mostrarConfirmacionFinal) {
                            mostrarConfirmacionFinal = true
                        } else {
                            // Guardar el curso como inscrito al presionar Entendido
                            if (!cursosInscritosIds.contains(curso.id)) {
                                cursosInscritosIds.add(curso.id)
                            }
                            cursoSeleccionadoParaInscripcion = null
                            mostrarConfirmacionFinal = false
                            correoUsuario = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = if (!mostrarConfirmacionFinal) "Enviar accesos" else "Entendido",
                        color = Color.White
                    )
                }
            },
            dismissButton = {
                if (!mostrarConfirmacionFinal) {
                    TextButton(onClick = { cursoSeleccionadoParaInscripcion = null }) {
                        Text("Cancelar", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        )
    }
}

@Composable
private fun TarjetaCursoItem(
    curso: CursoItem,
    estaInscrito: Boolean,
    onInscribirse: () -> Unit
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
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(curso.emoji, fontSize = 22.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = curso.titulo, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
                    Text(text = curso.institucion, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Fila de Óvalos / Badges (Duración, Modalidad e Inscrito)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(shape = RoundedCornerShape(50), color = MaterialTheme.colorScheme.primaryContainer) {
                    Text(curso.duracion, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = MoradoOscuro, modifier = Modifier.padding(8.dp, 4.dp))
                }
                Surface(shape = RoundedCornerShape(50), color = MaterialTheme.colorScheme.surfaceVariant) {
                    Text(curso.modalidad, fontSize = 11.sp, color = MoradoOscuro, modifier = Modifier.padding(8.dp, 4.dp))
                }

                // Óvalo Verde Pastel si ya está inscrita
                if (estaInscrito) {
                    Surface(shape = RoundedCornerShape(50), color = Color(0xFFD1E7DD)) {
                        Text("Inscrita", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F5132), modifier = Modifier.padding(8.dp, 4.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Cambiar botón a gris deshabilitado si ya está inscrita
            Button(
                onClick = onInscribirse,
                enabled = !estaInscrito,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Text(
                    text = if (estaInscrito) "Ya estás inscrita" else "Inscribirme gratis",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (estaInscrito) MaterialTheme.colorScheme.onSurfaceVariant else Color.White
                )
            }
        }
    }
}