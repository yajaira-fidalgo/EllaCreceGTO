package com.yajaira.crece.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun DetalleOportunidadScreen(
    oportunidad: Oportunidad,
    onVolver: () -> Unit,
    onPostularse: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de vacante", fontSize = 16.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Text("←", fontSize = 20.sp)
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Button(
                        onClick = onPostularse,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Quiero postularme", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Surface(
                shape = RoundedCornerShape(50),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Text(
                    text = "Vacante de ejemplo",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = MoradoOscuro,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = oportunidad.titulo,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MoradoOscuro
            )
            Text(
                text = "${oportunidad.empresa} · ${oportunidad.municipio}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${oportunidad.sueldo} / mes",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Etiquetas
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(shape = RoundedCornerShape(50), color = MaterialTheme.colorScheme.surfaceVariant) {
                    Text(oportunidad.modalidad, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp), color = MoradoOscuro)
                }
                Surface(shape = RoundedCornerShape(50), color = MaterialTheme.colorScheme.surfaceVariant) {
                    Text(oportunidad.tipoJornada, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp), color = MoradoOscuro)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Sobre la vacante
            Text("Sobre la vacante", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
            Spacer(modifier = Modifier.height(6.dp))
            Text(oportunidad.descripcion, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(modifier = Modifier.height(20.dp))

            // Requisitos
            Text("Lo que buscan", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = MoradoOscuro)
            Spacer(modifier = Modifier.height(8.dp))
            oportunidad.requisitos.forEach { req ->
                Row(modifier = Modifier.padding(vertical = 2.dp)) {
                    Text("• ", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    Text(req, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}