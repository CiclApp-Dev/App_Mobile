package com.example.ciclapp

import android.widget.Button

class ItemDeLaLista(
    private val mmarcaTelefono: String,
    private val mmodeloTelefono: String,
    private val mDescripcion: String,
    private val mImei: String
) {
    fun getmarcaTelefono(): String {
        return mmarcaTelefono
    }

    fun getmodeloTelefono(): String {
        return mmodeloTelefono
    }

    fun getdescripcion(): String {
        return mDescripcion
    }

    fun getImei(): String {
        return mImei
    }


}