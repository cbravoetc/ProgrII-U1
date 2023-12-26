package com.example.taller01.menu

class CuentaMesa(val mesa: Int) {
    val _items: MutableList<ItemMesa> = mutableListOf()
    var aceptaPropina: Boolean = true

    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        _items.add(ItemMesa(itemMenu, cantidad))
    }

    fun agregarItem(itemMesa: ItemMesa) {  //no debiera utilizarse pues es 1 mesa
        _items.add(itemMesa)
    }

    fun calcularTotalSinPropina(): Int {
        return _items.sumOf { it.calcularSubtotal() }
    }

    fun calcularPropina(): Int {
        if (aceptaPropina) {
            return (calcularTotalSinPropina() * 0.1).toInt()
        }
        return 0
    }

    fun calcularTotalConPropina(): Int {
        return calcularTotalSinPropina() + calcularPropina()
    }
}