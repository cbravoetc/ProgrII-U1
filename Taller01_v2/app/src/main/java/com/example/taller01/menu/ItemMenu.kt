package com.example.taller01.menu

open class ItemMenu(var nombre: String, var precio: String) {
    fun calcularSubtotal(cantidad: Int): Int {
        val precioInt = precio.toInt()
        return precioInt * cantidad
    }
}