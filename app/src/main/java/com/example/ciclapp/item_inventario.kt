package com.example.ciclapp


class Item_de_inventario(
    private val mmarcaTelefono: String,
    private val mmodeloTelefono: String,
    private val mRepuesto: String,
    private val mColor: String,
    private var mCantidad: String

) {
    fun getmarcaTelefono(): String {
        return mmarcaTelefono
    }

    fun getmodeloTelefono(): String {
        return mmodeloTelefono
    }

    fun getRepuesto(): String {
        return mRepuesto
    }

    fun getColor(): String {
        return mColor
    }

    fun getCantidad(): String {
        return mCantidad
    }

    fun setCantidad(cantidad: String){
        mCantidad = cantidad
    }

}