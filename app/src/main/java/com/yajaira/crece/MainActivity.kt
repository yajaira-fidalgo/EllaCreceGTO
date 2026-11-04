package com.yajaira.crece

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.yajaira.crece.ui.model.Oportunidad
import com.yajaira.crece.ui.screens.*
import com.yajaira.crece.ui.theme.CreceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreceTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    var pestañaSeleccionada by remember { mutableStateOf("inicio") }
    var pantallaActual by remember { mutableStateOf("bienvenida") }

    // Nombre del usuario en sesión
    var nombreUsuarioSesion by remember { mutableStateOf("Mariana") }

    var oportunidadSeleccionada by remember { mutableStateOf<Oportunidad?>(null) }
    val misPostulacionesList = remember { mutableStateListOf<Oportunidad>() }

    Scaffold(
        bottomBar = {
            val pantallasSinBottomBar = listOf("bienvenida", "registro", "login", "detalle_oportunidad", "mis_postulaciones", "apoyos")
            if (pantallaActual !in pantallasSinBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    NavigationBarItem(
                        selected = pestañaSeleccionada == "inicio",
                        onClick = { pestañaSeleccionada = "inicio"; pantallaActual = "inicio" },
                        label = { Text("Inicio") },
                        icon = { Text("🏠") }
                    )
                    NavigationBarItem(
                        selected = pestañaSeleccionada == "empleos",
                        onClick = { pestañaSeleccionada = "empleos"; pantallaActual = "empleos" },
                        label = { Text("Empleos") },
                        icon = { Text("💼") }
                    )
                    NavigationBarItem(
                        selected = pestañaSeleccionada == "negocios",
                        onClick = { pestañaSeleccionada = "negocios"; pantallaActual = "negocios" },
                        label = { Text("Negocios") },
                        icon = { Text("🛍️") }
                    )
                    NavigationBarItem(
                        selected = pestañaSeleccionada == "cursos",
                        onClick = { pestañaSeleccionada = "cursos"; pantallaActual = "cursos" },
                        label = { Text("Cursos") },
                        icon = { Text("📖") }
                    )
                    NavigationBarItem(
                        selected = pestañaSeleccionada == "perfil",
                        onClick = { pestañaSeleccionada = "perfil"; pantallaActual = "perfil" },
                        label = { Text("Perfil") },
                        icon = { Text("👤") }
                    )
                }
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            when (pantallaActual) {
                "bienvenida" -> {
                    BienvenidaScreen(
                        onIrARegistro = { pantallaActual = "registro" },
                        onIrAIniciarSesion = { pantallaActual = "login" }
                    )
                }

                "login" -> {
                    LoginScreen(
                        onLoginExitoso = { nombreIngresado ->
                            nombreUsuarioSesion = nombreIngresado
                            pantallaActual = "inicio"
                            pestañaSeleccionada = "inicio"
                        },
                        onVolver = { pantallaActual = "bienvenida" }
                    )
                }

                "registro" -> {
                    RegistroScreen(
                        onRegistroCompletado = { nombreRegistrado ->
                            nombreUsuarioSesion = nombreRegistrado
                            pantallaActual = "inicio"
                            pestañaSeleccionada = "inicio"
                        },
                        onIrALogin = { pantallaActual = "login" }
                    )
                }

                "inicio" -> {
                    DescubreTalentoScreen(
                        nombreUsuario = nombreUsuarioSesion,
                        onNavegarA = { destino ->
                            pantallaActual = destino
                            if (destino in listOf("inicio", "empleos", "negocios", "cursos", "perfil")) {
                                pestañaSeleccionada = destino
                            }
                        }
                    )
                }

                "empleos" -> {
                    OportunidadesScreen(
                        onSeleccionarOportunidad = { item ->
                            oportunidadSeleccionada = item
                            pantallaActual = "detalle_oportunidad"
                        },
                        onVerMisPostulaciones = { pantallaActual = "mis_postulaciones" },
                        onVolverAInicio = { pantallaActual = "inicio"; pestañaSeleccionada = "inicio" }
                    )
                }

                "detalle_oportunidad" -> {
                    oportunidadSeleccionada?.let { empleo ->
                        DetalleOportunidadScreen(
                            oportunidad = empleo,
                            onVolver = { pantallaActual = "empleos" },
                            onPostularse = {
                                if (!misPostulacionesList.any { it.id == empleo.id }) {
                                    misPostulacionesList.add(empleo)
                                }
                                pantallaActual = "mis_postulaciones"
                            }
                        )
                    }
                }

                "mis_postulaciones" -> {
                    MisPostulacionesScreen(
                        postulaciones = misPostulacionesList,
                        onVolver = { pantallaActual = "empleos" }
                    )
                }

                "apoyos" -> {
                    ApoyosScreen(
                        onVolverAInicio = { pantallaActual = "inicio"; pestañaSeleccionada = "inicio" }
                    )
                }

                "negocios" -> {
                    PasaporteTalentoScreen(
                        onVerOportunidades = { pantallaActual = "empleos"; pestañaSeleccionada = "empleos" },
                        onVolverAInicio = { pantallaActual = "inicio"; pestañaSeleccionada = "inicio" }
                    )
                }

                "cursos" -> {
                    MentoraIAScreen(
                        onVolver = { pantallaActual = "inicio"; pestañaSeleccionada = "inicio" }
                    )
                }

                "perfil" -> {
                    AjustesAccesibilidadScreen(
                        onVolver = { pantallaActual = "inicio"; pestañaSeleccionada = "inicio" },
                        onCerrarSesion = { pantallaActual = "bienvenida"; pestañaSeleccionada = "inicio" }
                    )
                }
            }
        }
    }
}