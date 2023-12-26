package com.example.taller01.menu

class ItemMesa(val itemMenu: ItemMenu, val cantidad: Int) {
    fun calcularSubtotal(): Int {
        return itemMenu.calcularSubtotal(cantidad)
    }
}