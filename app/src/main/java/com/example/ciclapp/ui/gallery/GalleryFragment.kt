package com.example.ciclapp.ui.gallery

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import adaptador_match
import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ciclapp.*
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlin.collections.ArrayList

class GalleryFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        recargar = 1
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        val ejemplo_lista_match = ArrayList<ItemDeLaLista>()
        root.recyclerView2.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        val mAdapter = adaptador_match(ejemplo_lista_match)

        root.recyclerView2.setLayoutManager(mLayoutManager)
        root.recyclerView2.setAdapter(mAdapter)


        mAdapter.setOnItemClickListener(object : adaptador_match.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val imei_seleccionado = ejemplo_lista_match.get(position).getImei()
                (activity as MainActivity?)?.mostrar_datos(imei_seleccionado)
            }
        })

        root.boton_seleccionar.setOnClickListener {
            if(root.spinner.selectedItem.toString() == "Seleccione"){
                (activity as MainActivity?)?.mostrar_toast("No se ha seleccionado ninguna marca")
            }else{
                val marca_seleccionada = root.spinner.selectedItem.toString()

                var conjunto = otros
                if(marca_seleccionada == "Samsung"){
                    conjunto = samsung
                }else if(marca_seleccionada == "Apple"){
                    conjunto = apple
                }else if(marca_seleccionada == "Motorola"){
                    conjunto = motorola
                }else if(marca_seleccionada == "Sony"){
                    conjunto = sony
                }else if(marca_seleccionada == "LG"){
                    conjunto = lg
                }else if(marca_seleccionada == "Huawei"){
                    conjunto = huawei
                }else if(marca_seleccionada == "Alcatel"){
                    conjunto = alcatel
                }

                val spinnerLoco = SpinnerDialog(activity, conjunto, marca_seleccionada)

                spinnerLoco.setTitleColor(getResources().getColor(R.color.colorBlack))
                spinnerLoco.setSearchIconColor(getResources().getColor(R.color.colorBlack))
                spinnerLoco.setSearchTextColor(getResources().getColor(R.color.colorBlack))
                spinnerLoco.setItemColor(getResources().getColor(R.color.colorBlack))
                spinnerLoco.setItemDividerColor(getResources().getColor(R.color.colorBlack))
                spinnerLoco.setCloseColor(getResources().getColor(R.color.colorBlack))

                spinnerLoco.bindOnSpinerListener { item, position ->
                    root.textView47.text = item
                }
                spinnerLoco.showSpinerDialog()
            }
        }

        root.boton_buscar.setOnClickListener {
            if(root.spinner.selectedItem.toString() == "Seleccione" || root.textView47.text.toString() == "Seleccione un modelo"){
                (activity as MainActivity?)?.mostrar_toast("No se ha seleccionado ninguna marca o modelo")
            }else{
                val msg = "," + root.spinner.selectedItem.toString() + "," + root.textView47.text.toString()

                (activity as MainActivity?)?.enviar_mensaje(msg)

                if(retornos_hilo == ""){
                    (activity as MainActivity?)?.mostrar_toast("No hay matches disponibles o el telefono no ha sido ponderado")
                }else if(retornos_hilo == "nada nadita"){
                    //Pos aca no se hace nada xd
                }else{
                    val lista_telefonos = retornos_hilo.split("&")
                    ejemplo_lista_match.clear()
                    for(a in lista_telefonos) {
                        val telefono_actual = a.split(",")
                        val descripcion =
                            "Riego: " + telefono_actual[1] + " | Tiempo: " + telefono_actual[2] + " | IMEI: " + telefono_actual[0]
                        ejemplo_lista_match.add(
                            ItemDeLaLista(
                                root.spinner.selectedItem.toString() + " ",
                                root.textView47.text.toString(),
                                descripcion,
                                telefono_actual[0]
                            )
                        )
                    }
                    mAdapter.notifyDataSetChanged()
                }
            }
        }


        return root
    }
}
