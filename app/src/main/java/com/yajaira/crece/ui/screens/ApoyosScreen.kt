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

data class ApoyoItem(
    val id: String,
    val titulo: String,
    val programa: String,
    val montoOBeneficio: String,
    val municipio: String,
    val estatus: String,
    val descripcion: String,
    val emoji: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApoyosScreen(
    onVolverAInicio: () -> Unit
) {
    var textoBusqueda by remember { mutableStateOf("") }

    // Lista de IDs de apoyos solicitados por la usuaria
    val apoyosSolicitadosIds = remember { mutableStateListOf<String>() }

    // Estados para el flujo de solicitud
    var apoyoSeleccionado by remember { mutableStateOf<ApoyoItem?>(null) }
    var curpONumero by remember { mutableStateOf("") }
    var solicitudIniciada by remember { mutableStateOf(false) }

    val listaApoyos = listOf(
        ApoyoItem(
            id = "1",
            titulo = "Equipamiento para Mi Negocio",
            programa = "Fondos Guanajuato",
            montoOBeneficio = "Hasta $25,000 MXN en maquinaria/equipo",
            municipio = "Estatal (46 municipios)",
            estatus = "Convocatoria Abierta",
            descripcion = "Apoyo directo en especie para la compra de herramientas, máquinas de coser, hornos o insumos de trabajo.",
            emoji = "🛠️"
        ),
        ApoyoItem(
            id = "2",
            titulo = "Beca Mujeres Adelante",
            programa = "Secretaría de Desarrollo Social GTO",
            montoOBeneficio = "$3,500 MXN mensuales por 6 meses",
            municipio = "León, Irapuato, Celaya",
            estatus = "Convocatoria Abierta",
            descripcion = "Incentivo económico para mujeres que se encuentran capacitándose en algún taller de oficio del IECA.",
            emoji = "🎓"
        ),
        ApoyoItem(
            id = "3",
            titulo = "Crédito Rosa Emprendedora",
            programa = "Marca GTO",
            montoOBeneficio = "Financiamiento desde $10,000 a $50,000 MXN",
            municipio = "Estatal",
            estatus = "Registro Permanente",
            descripcion = "Tasa de interés preferencial con periodo de gracia para arranque o consolidación de micro negocios.",
            emoji = "💳"
        )
    )

    // Buscador funcional que filtra por título, programa o municipio
    val apoyosFiltrados = listaApoyos.filter {
        it.titulo.contains(textoBusqueda, ignoreCase = true) ||
                it.programa.contains(textoBusqueda, ignoreCase = true) ||
                it.municipio.contains(textoBusqueda, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Apoyos y Programas GTO", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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
                text = "IMPULSO A TU ECONOMÍA",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.sp
            )
            Text(
                text = "Becas, Créditos y Equipo",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MoradoOscuro
            )
            Text(
                text = "Programas de apoyo del Gobierno de Guanajuato diseñados para impulsar a las mujeres.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Buscador Interactivo
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                placeholder = { Text("Buscar beca, crédito o equipo...", fontSize = 14.sp) },
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
                    text = "Programas disponibles",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoradoOscuro
                )
                Text(
                    text = "${apoyosFiltrados.size} convocatorias",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Lista de Tarjetas de Apoyos
            apoyosFiltrados.forEach { apoyo ->
                TarjetaApoyoItem(
                    apoyo = apoyo,
                    estaSolicitado = apoyosSolicitadosIds.contains(apoyo.id),
                    onSolicitar = {
                        apoyoSeleccionado = apoyo
                        solicitudIniciada = false
                        curpONumero = ""
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    // Modal de Solicitud de Apoyo
    apoyoSeleccionado?.let { apoyo ->
        AlertDialog(
            onDismissRequest = { apoyoSeleccionado = null },
            title = {
                Text(
                    text = if (!solicitudIniciada) "Registro de Programa" else "🎉 ¡Pre-registro exitoso!",
                    fontWeight = FontWeight.Bold,
                    color = MoradoOscuro
                )
            },
            text = {
                if (!solicitudIniciada) {
                    Column {
                        Text(
                            text = "Estás por solicitar el programa '${apoyo.titulo}' (${apoyo.programa}).",
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Beneficio: ${apoyo.montoOBeneficio}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Ingresa tu CURP o teléfono de contacto para validar tu solicitud:",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = curpONumero,
                            onValueChange = { curpONumero = it },
                            placeholder = { Text("CURP o Teléfono a 10 dígitos", fontSize = 12.sp) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                    }
                } else {
                    Text(
                        text = "Hemos registrado con éxito tu pre-solicitud para '${apoyo.titulo}'.\n\nRecibirás confirmación y folio de seguimiento en tu cuenta.",
                        fontSize = 13.sp
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (!solicitudIniciada) {
                            solicitudIniciada = true
                        } else {
                            // Agregar a la lista de solicitados al presionar Entendido
                            if (!apoyosSolicitadosIds.contains(apoyo.id)) {
                                apoyosSolicitadosIds.add(apoyo.id)
                            }
                            apoyoSeleccionado = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = if (!solicitudIniciada) "Confirmar pre-registro" else "Entendido",
                        color = Color.White
                    )
                }
            },
            dismissButton = {
                if (!solicitudIniciada) {
                    TextButton(onClick = { apoyoSeleccionado = null }) {
                        Text("Cancelar", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        )
    }
}

@Composable
private fun TarjetaApoyoItem(
    apoyo: ApoyoItem,
    estaSolicitado: Boolean,
    onSolicitar: () -> Unit
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
                        Text(apoyo.emoji, fontSize = 22.sp)
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = apoyo.titulo, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
                    Text(text = "${apoyo.programa} · ${apoyo.municipio}", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = apoyo.descripcion,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = apoyo.montoOBeneficio,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MoradoOscuro,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                // Etiqueta verde si el apoyo ya fue solicitado
                if (estaSolicitado) {
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = Color(0xFFD1E7DD)
                    ) {
                        Text(
                            text = "✓ Apoyo solicitado",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F5132),
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Botón que cambia a deshabilitado cuando ya está solicitado
            Button(
                onClick = onSolicitar,
                enabled = !estaSolicitado,
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
                    text = if (estaSolicitado) "Solicitado" else "Solicitar apoyo",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (estaSolicitado) MaterialTheme.colorScheme.onSurfaceVariant else Color.White
                )
            }
        }
    }
}