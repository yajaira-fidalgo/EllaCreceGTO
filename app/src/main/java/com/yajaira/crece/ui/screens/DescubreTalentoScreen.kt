package com.yajaira.crece.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

@Composable
fun DescubreTalentoScreen(
    nombreUsuario: String = "Mariana",
    onNavegarA: (String) -> Unit
) {
    // Estado para controlar la ventana flotante de Notificaciones
    var mostrarNotificaciones by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Cabecera con ubicación y notificación interactiva
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "📍 LEÓN, GUANAJUATO",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Hola, $nombreUsuario",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoradoOscuro
                )
                Text(
                    text = "Lo que sabes hacer abre nuevos caminos.",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            // Botón de campanita conectado
            IconButton(onClick = { mostrarNotificaciones = true }) {
                Text(text = "🔔", fontSize = 20.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Banner del día
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "💡", fontSize = 22.sp, modifier = Modifier.padding(end = 10.dp))
                Column {
                    Text(
                        text = "Hoy es un buen día para crecer",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MoradoOscuro
                    )
                    Text(
                        text = "Tu espacio, tus metas, tu ritmo.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Buscador
        OutlinedTextField(
            value = "",
            onValueChange = { },
            placeholder = { Text("¿Qué te gustaría hacer hoy?", fontSize = 14.sp) },
            leadingIcon = { Text("🔍") },
            trailingIcon = { Text("🎛️") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // GRID de 4 accesos directos
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TarjetaGridAcceso(
                    titulo = "Empleos",
                    subtitulo = "Encuentra tu lugar",
                    icono = "💼",
                    colorFondo = Color(0xFFEADBEE),
                    onClick = { onNavegarA("empleos") },
                    modifier = Modifier.weight(1f)
                )
                TarjetaGridAcceso(
                    titulo = "Mi negocio",
                    subtitulo = "Comparte tu talento",
                    icono = "🛍️",
                    colorFondo = Color(0xFFF7EFF8),
                    onClick = { onNavegarA("negocios") },
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TarjetaGridAcceso(
                    titulo = "Cursos",
                    subtitulo = "Aprende a tu ritmo",
                    icono = "📖",
                    colorFondo = Color(0xFFFFF3D6),
                    onClick = { onNavegarA("cursos") },
                    modifier = Modifier.weight(1f)
                )
                TarjetaGridAcceso(
                    titulo = "Apoyos",
                    subtitulo = "Da el siguiente paso",
                    icono = "🤝",
                    colorFondo = Color(0xFFE0F2FE),
                    onClick = { onNavegarA("apoyos") },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Alerta de entrevista
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "📅 ¡Tienes una entrevista!",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoradoOscuro
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Puntadas del Bajío · Auxiliar de costura. Tu cita: 12 de noviembre, 10:00 a. m.",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Sección: Una oportunidad para ti
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Una oportunidad para ti",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MoradoOscuro
            )
            TextButton(onClick = { onNavegarA("empleos") }) {
                Text("Ver empleos", color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Tarjeta de Vacante Recomendada
        Card(
            onClick = { onNavegarA("empleos") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Surface(shape = RoundedCornerShape(50), color = MaterialTheme.colorScheme.surfaceVariant) {
                        Text("León", fontSize = 10.sp, modifier = Modifier.padding(6.dp, 2.dp), color = MoradoOscuro)
                    }
                    Surface(shape = RoundedCornerShape(50), color = MaterialTheme.colorScheme.surfaceVariant) {
                        Text("Presencial", fontSize = 10.sp, modifier = Modifier.padding(6.dp, 2.dp), color = MoradoOscuro)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Costurera de confección", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
                Text("Taller Más Vivo · León", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("$9,000–$11,000 MXN al mes", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }

    // Modal de Notificaciones Recientes
    if (mostrarNotificaciones) {
        AlertDialog(
            onDismissRequest = { mostrarNotificaciones = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("🔔 ", fontSize = 20.sp)
                    Text("Tus Notificaciones", fontWeight = FontWeight.Bold, color = MoradoOscuro)
                }
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    ItemNotificacion(
                        icono = "📅",
                        titulo = "Recordatorio de entrevista",
                        detalle = "Puntadas del Bajío confirmó tu cita para el 12 de noviembre.",
                        tiempo = "Hace 10 min"
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp), color = MaterialTheme.colorScheme.surfaceVariant)
                    ItemNotificacion(
                        icono = "🎓",
                        titulo = "Nueva beca disponible",
                        detalle = "Se abrió la convocatoria 'Mujeres Adelante GTO'.",
                        tiempo = "Hace 2 horas"
                    )
                    Divider(modifier = Modifier.padding(vertical = 8.dp), color = MaterialTheme.colorScheme.surfaceVariant)
                    ItemNotificacion(
                        icono = "✨",
                        titulo = "Mentora IA",
                        detalle = "Hay nuevos cursos de confección textil esperándote.",
                        tiempo = "Ayer"
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { mostrarNotificaciones = false },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Entendido", color = Color.White)
                }
            }
        )
    }
}

@Composable
private fun ItemNotificacion(
    icono: String,
    titulo: String,
    detalle: String,
    tiempo: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Text(icono, fontSize = 20.sp, modifier = Modifier.padding(end = 10.dp, top = 2.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(titulo, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
                Text(tiempo, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(detalle, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Composable
private fun TarjetaGridAcceso(
    titulo: String,
    subtitulo: String,
    icono: String,
    colorFondo: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = colorFondo)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = icono, fontSize = 24.sp)
            Column {
                Text(text = titulo, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
                Text(text = subtitulo, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}