package com.yajaira.crece.ui.model

enum class EstadoPostulacion {
    EN_REVISION,
    INVITACION_ENTREVISTA,
    NO_SELECCIONADO,
    CONTRATADO
}

data class Postulacion(
    val id: String,
    val oportunidad: Oportunidad,
    val fecha: String,
    val estado: EstadoPostulacion,
    val mensajeTaller: String? = null
)