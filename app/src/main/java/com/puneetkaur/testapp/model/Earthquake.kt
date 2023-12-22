package com.puneetkaur.testapp.model

data class Earthquake(
    val datetime: String,
    val depth: Double,
    val eqid: String,
    val lat: Double,
    val lng: Double,
    val magnitude: Double,
    val src: String
)