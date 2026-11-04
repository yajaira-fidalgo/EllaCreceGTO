package com.yajaira.crece.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yajaira.crece.ui.theme.MoradoOscuro

@Composable
fun BienvenidaScreen(
    onIrARegistro: () -> Unit,
    onIrAIniciarSesion: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Cabecera superior: Logo / Marca
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(
                text = "✨ Ella Crece GTO",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MoradoOscuro
            )
            Text(
                text = "TU TALENTO TIENE FUTURO",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 1.2.sp
            )
        }

        // Tarjeta central con mensaje inspirador y chips
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Badge superior
                Surface(
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Text(
                        text = "Hecho para mujeres de Guanajuato",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }

                Text(
                    text = "Que te conozcan por lo que puedes hacer, no por de dónde vienes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MoradoOscuro,
                    textAlign = TextAlign.Center,
                    lineHeight = 26.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Encuentra empleo, aprende algo nuevo y haz crecer tu negocio. Aquí, tu siguiente paso es posible.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Chips / Tags
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ChipTag(texto = "Trabaja")
                    ChipTag(texto = "Aprende")
                    ChipTag(texto = "Emprende")
                }
            }
        }

        // Botones de Acción
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onIrARegistro,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Crear mi cuenta",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            OutlinedButton(
                onClick = onIrAIniciarSesion,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MoradoOscuro)
            ) {
                Text(
                    text = "Ya tengo cuenta · Iniciar sesión",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Un espacio para avanzar a tu ritmo, con confianza y en comunidad.",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ChipTag(texto: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Text(
            text = texto,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = MoradoOscuro,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
        )
    }
}