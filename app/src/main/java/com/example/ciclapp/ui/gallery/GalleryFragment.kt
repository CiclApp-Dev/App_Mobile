package com.example.ciclapp.ui.gallery

import adaptador
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ciclapp.ItemDeLaLista
import com.example.ciclapp.MainActivity
import com.example.ciclapp.R
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlin.collections.ArrayList

class GalleryFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        var ejemplo_lista = ArrayList<ItemDeLaLista>()
        ejemplo_lista.add(ItemDeLaLista("Samsung ", "Galaxy S7 Edge", "Riesgo: 15 | Tiempo: 13 | IMEI: 123456789", "123456789"))
        ejemplo_lista.add(ItemDeLaLista("Motorola ", "Moto E (2nd Gen)", "Riesgo: 3 | Tiempo: 5 | IMEI: 999999999", "999999999"))

        root.recyclerView2.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        val mAdapter = adaptador(ejemplo_lista)

        root.recyclerView2.setLayoutManager(mLayoutManager)
        root.recyclerView2.setAdapter(mAdapter)

        mAdapter.setOnItemClickListener(object : adaptador.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val imei_seleccionado = ejemplo_lista.get(position).getImei()
                (activity as MainActivity?)?.mostrar_datos(imei_seleccionado)
            }
        })

        return root
    }
}
