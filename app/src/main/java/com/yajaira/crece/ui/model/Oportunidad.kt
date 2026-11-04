package com.yajaira.crece.ui.model

data class Oportunidad(
    val id: String,
    val titulo: String,
    val empresa: String,
    val municipio: String,
    val modalidad: String,
    val sueldo: String,
    val tipoJornada: String,
    val descripcion: String,
    val requisitos: List<String>,
    val esEjemplo: Boolean = false
)