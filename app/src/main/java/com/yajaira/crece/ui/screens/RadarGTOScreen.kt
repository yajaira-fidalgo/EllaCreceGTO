package com.yajaira.crece.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ApoyoGTO(
    val titulo: String,
    val institucion: String,
    val municipio: String,
    val tipo: String,
    val descripcion: String
)

@Composable
fun RadarGTOScreen(
    onVolver: () -> Unit
) {
    val apoyosMock = listOf(
        ApoyoGTO(
            titulo = "Capacitación en Herramientas Digitales y Finanzas",
            institucion = "Secretaría de las Mujeres GTO",
            municipio = "Irapuato",
            tipo = "Taller Gratuito",
            descripcion = "Aprende a gestionar costos, cobros digitales y catálogo en línea para tu negocio local."
        ),
        ApoyoGTO(
            titulo = "Programa de Equipamiento Productivo",
            institucion = "Desarrollo Económico Municipal",
            municipio = "Pénjamo",
            tipo = "Subsidio / Apoyo",
            descripcion = "Convocatoria para obtener maquinaria básica o insumos para emprendedoras registradas."
        ),
        ApoyoGTO(
            titulo = "Asesoría Legal y Mentoría de Negocios",
            institucion = "Red de Mentoras GTO",
            municipio = "Abasolo",
            tipo = "Acompañamiento 1 a 1",
            descripcion = "Sesiones personalizadas para la formalización de microempresas y registro de marca."
        ),
        ApoyoGTO(
            titulo = "Estrategias de Comercialización y Exportación Local",
            institucion = "Marca GTO",
            municipio = "León",
            tipo = "Certificación",
            descripcion = "Vinculación directa para colocar tus productos en ferias regionales y tiendas oficiales."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { onVolver() }) {
                Text("⬅ Volver")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Radar GTO",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Text(
            text = "Apoyos, talleres y recursos gubernamentales cerca de ti.",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(start = 8.dp, bottom = 12.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(apoyosMock) { apoyo ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Badge(containerColor = MaterialTheme.colorScheme.secondary) {
                                Text(
                                    text = apoyo.tipo,
                                    color = MaterialTheme.colorScheme.onSecondary,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                            Text(
                                text = "📍 ${apoyo.municipio}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = apoyo.titulo,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )

                        Text(
                            text = apoyo.institucion,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.outline
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = apoyo.descripcion,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}