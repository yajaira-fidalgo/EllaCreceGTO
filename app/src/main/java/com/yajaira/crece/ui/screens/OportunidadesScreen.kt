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
import com.yajaira.crece.ui.model.Oportunidad
import com.yajaira.crece.ui.theme.MoradoOscuro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OportunidadesScreen(
    onSeleccionarOportunidad: (Oportunidad) -> Unit,
    onVerMisPostulaciones: () -> Unit,
    onVolverAInicio: () -> Unit
) {
    var textoBusqueda by remember { mutableStateOf("") }
    var mostrarSheetFiltros by remember { mutableStateOf(false) }
    var filtroModalidad by remember { mutableStateOf("Todas") }

    // Lista de empleos simulados según prototipo Figma
    val listaEmpleos = listOf(
        Oportunidad(
            id = "1",
            titulo = "Costurera de confección",
            empresa = "Taller Más Vivo",
            municipio = "León",
            modalidad = "Presencial",
            sueldo = "$9,000–$11,000 MXN",
            tipoJornada = "Tiempo completo",
            descripcion = "Forma parte de un taller de producción textil local. Buscamos manos cuidadosas y pasión por el detalle.",
            requisitos = listOf("Experiencia en máquina recta", "Atención al detalle", "Disponibilidad inmediata"),
            esEjemplo = true
        ),
        Oportunidad(
            id = "2",
            titulo = "Auxiliar de costura y acabados",
            empresa = "Puntadas del Bajío",
            municipio = "León",
            modalidad = "Presencial",
            sueldo = "$8,500–$9,500 MXN",
            tipoJornada = "Tiempo completo",
            descripcion = "Taller familiar busca auxiliar para empaque, control de calidad y acabados de prenda.",
            requisitos = listOf("Ganas de aprender", "Trabajo en equipo", "Puntualidad"),
            esEjemplo = true
        ),
        Oportunidad(
            id = "3",
            titulo = "Asesora de ventas y atención",
            empresa = "Raíces Mercado Local",
            municipio = "Silao",
            modalidad = "Híbrido",
            sueldo = "$8,000–$10,000 MXN",
            tipoJornada = "Medio tiempo",
            descripcion = "Atención a clientes para catálogo de artesanías y productos locales en punto de venta y WhatsApp.",
            requisitos = listOf("Facilidad de palabra", "Uso de celular/WhatsApp", "Gusto por las ventas"),
            esEjemplo = true
        )
    )

    // Filtrar la lista dinámicamente según lo que escriba el usuario
    val empleosFiltrados = listaEmpleos.filter { empleo ->
        val coincideBusqueda = empleo.titulo.contains(textoBusqueda, ignoreCase = true) ||
                empleo.empresa.contains(textoBusqueda, ignoreCase = true) ||
                empleo.municipio.contains(textoBusqueda, ignoreCase = true)
        val coincideModalidad = if (filtroModalidad == "Todas") true else empleo.modalidad.equals(filtroModalidad, ignoreCase = true)

        coincideBusqueda && coincideModalidad
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Trabajo con posibilidades", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
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
                text = "TRABAJO CON POSIBILIDADES",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.sp
            )
            Text(
                text = "Tu talento encuentra lugar",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MoradoOscuro
            )
            Text(
                text = "Busca un empleo que vaya contigo.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Buscador Dinámico
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                placeholder = { Text("Puesto, oficio o municipio...", fontSize = 14.sp) },
                leadingIcon = { Text("🔍") },
                trailingIcon = {
                    if (textoBusqueda.isNotEmpty()) {
                        IconButton(onClick = { textoBusqueda = "" }) { Text("✕", fontSize = 14.sp) }
                    } else {
                        Text("🎛️")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Chips con acción interactiva
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                FilterChipTag(
                    texto = "León y cercanos ▾",
                    seleccionado = true,
                    onClick = { mostrarSheetFiltros = true }
                )
                FilterChipTag(
                    texto = if (filtroModalidad == "Todas") "Modalidad ▾" else "Modalidad: $filtroModalidad",
                    seleccionado = filtroModalidad != "Todas",
                    onClick = { mostrarSheetFiltros = true }
                )
                FilterChipTag(
                    texto = "Más filtros",
                    seleccionado = false,
                    onClick = { mostrarSheetFiltros = true }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Banner mis postulaciones interactivo
            Card(
                onClick = onVerMisPostulaciones,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("📋", fontSize = 20.sp, modifier = Modifier.padding(end = 10.dp))
                        Text(
                            text = "Mis postulaciones · Ver solicitudes",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MoradoOscuro
                        )
                    }
                    Text("›", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Para tu experiencia",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoradoOscuro
                )
                Text(
                    text = "${empleosFiltrados.size} resultados",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Renderizar la lista filtrada
            if (empleosFiltrados.isEmpty()) {
                Text(
                    text = "No se encontraron vacantes con los criterios ingresados.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
            } else {
                empleosFiltrados.forEach { empleo ->
                    TarjetaEmpleoItem(
                        titulo = empleo.titulo,
                        empresa = "${empleo.empresa} · ${empleo.municipio}",
                        sueldo = "${empleo.sueldo} / mes",
                        tags = listOf(empleo.modalidad, empleo.tipoJornada),
                        haceCuanto = "Disponible",
                        onClick = { onSeleccionarOportunidad(empleo) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    // Modal Flotante de Filtros
    if (mostrarSheetFiltros) {
        ModalBottomSheet(
            onDismissRequest = { mostrarSheetFiltros = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Filtrar vacantes",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoradoOscuro
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Modalidad de trabajo:", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChipTag(
                        texto = "Todas",
                        seleccionado = filtroModalidad == "Todas",
                        onClick = { filtroModalidad = "Todas"; mostrarSheetFiltros = false }
                    )
                    FilterChipTag(
                        texto = "Presencial",
                        seleccionado = filtroModalidad == "Presencial",
                        onClick = { filtroModalidad = "Presencial"; mostrarSheetFiltros = false }
                    )
                    FilterChipTag(
                        texto = "Híbrido",
                        seleccionado = filtroModalidad == "Híbrido",
                        onClick = { filtroModalidad = "Híbrido"; mostrarSheetFiltros = false }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun FilterChipTag(texto: String, seleccionado: Boolean, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(50),
        color = if (seleccionado) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
        border = if (!seleccionado) androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline) else null,
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = texto,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = MoradoOscuro,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun TarjetaEmpleoItem(
    titulo: String,
    empresa: String,
    sueldo: String,
    tags: List<String>,
    haceCuanto: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("✂️", fontSize = 18.sp)
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(text = titulo, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
                        Text(text = empresa, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
                IconButton(onClick = { }) {
                    Text("🔖", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = sueldo, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                tags.forEach { tag ->
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = tag,
                            fontSize = 10.sp,
                            color = MoradoOscuro,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = haceCuanto, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = "Ver detalle ↗", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}