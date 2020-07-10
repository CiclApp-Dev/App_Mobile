package com.example.ciclapp.ui.slideshow

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import adaptador_inventario
import android.app.AlertDialog
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ciclapp.*
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlinx.android.synthetic.main.fragment_slideshow.view.*

class SlideshowFragment : Fragment() {
    var marca = ""
    var modelo = ""

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        recargar = 1

        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        val ejemplo_lista = ArrayList<Item_de_inventario>()


        root.recyclerView_inventario.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        val mAdapter = adaptador_inventario(ejemplo_lista)

        root.recyclerView_inventario.setLayoutManager(mLayoutManager)
        root.recyclerView_inventario.setAdapter(mAdapter)

        root.button4.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Agregar un repuesto")
            builder.setMessage("Ingrese los siguientes detalles")

            val layout = LinearLayout(activity)
            layout.setOrientation(LinearLayout.VERTICAL)


            val linea1 = LinearLayout(activity)
            val texto1 = TextView(activity)
            texto1.text = "Repuesto: "
            texto1.setWidth(200)
            val caja1 = EditText(activity)
            caja1.setHint("Ingrese el nombre")
            caja1.setWidth(500)
            linea1.addView(texto1)
            linea1.addView(caja1)

            val linea2 = LinearLayout(activity)
            val texto2 = TextView(activity)
            texto2.text = "Cantidad: "
            texto2.setWidth(200)
            val caja2 = EditText(activity)
            caja2.setHint("Cantidad")
            caja2.setWidth(500)
            caja2.setInputType(InputType.TYPE_CLASS_NUMBER)
            linea2.addView(texto2)
            linea2.addView(caja2)

            val linea3 = LinearLayout(activity)
            val texto3 = TextView(activity)
            texto3.text = "Color: "
            texto3.setWidth(200)
            val caja3 = EditText(activity)
            caja3.setHint("Ingrese el color")
            caja3.setWidth(500)
            linea3.addView(texto3)
            linea3.addView(caja3)


            layout.addView(linea1)
            layout.addView(linea2)
            layout.addView(linea3)

            var cantidad = caja2.text.toString()
            if(cantidad == ""){
                cantidad = "0"
            }

            builder.setNegativeButton("Cancelar"){dialog,which -> }
            builder.setPositiveButton("Agregar"){dialog,which ->
                ejemplo_lista.add(Item_de_inventario(marca, modelo, caja1.text.toString(), caja3.text.toString(), caja2.text.toString()))
            }

            builder.setView(layout)
            builder.show()


        }

        root.button_inventario.setOnClickListener {
            if(root.spinner_inventario.selectedItem.toString() == "Seleccione" || root.textView3_inventario.text.toString() == "Seleccione un modelo"){
                (activity as MainActivity?)?.mostrar_toast("No se ha seleccionado ninguna marca o modelo")
            }else{
                val devolucion = (activity as MainActivity?)?.enviar_mensaje("6," + root.spinner_inventario.selectedItem.toString() + "," + root.textView3_inventario.text.toString())
                if(devolucion == "Error"){

                }else{
                    ejemplo_lista.clear()
                    val lista_telefonos = devolucion!!.split("&")

                    for(a in lista_telefonos){
                        val repuesto = a.split(",")
                        ejemplo_lista.add(Item_de_inventario(repuesto[0], repuesto[1], repuesto[2], repuesto[4], repuesto[3]))
                    }
                    mAdapter.notifyDataSetChanged()
                }
            }
        }

        root.button2_inventario.setOnClickListener {
            if(root.spinner_inventario.selectedItem.toString() == "Seleccione"){
                (activity as MainActivity?)?.mostrar_toast("No se ha seleccionado ninguna marca")
            }else{
                ejemplo_lista.clear()
                mAdapter.notifyDataSetChanged()
                val marca_seleccionada = root.spinner_inventario.selectedItem.toString()

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
                    root.textView3_inventario.text = item
                    marca = marca_seleccionada
                    modelo = item
                }
                spinnerLoco.showSpinerDialog()
            }
        }

        root.button3.setOnClickListener {
            if (ejemplo_lista.size == 0){
                (activity as MainActivity?)?.mostrar_toast("No hay items para actualizar")
            }else{
                var msg = ""
                for(a in ejemplo_lista){
                    msg += marca + "@/@" + modelo + "@/@" + a.getRepuesto() + "@/@" + a.getCantidad() + "@/@" + a.getColor() + "*/*"
                }
                msg = "7," + msg.substring(0,msg.length - 3)
                Log.d("Tagcito", msg )
                val respuesta = (activity as MainActivity?)?.enviar_mensaje(msg)
                if (respuesta != "Error"){
                    (activity as MainActivity?)?.mostrar_toast("Base de datos actualizada")
                    ejemplo_lista.clear()

                }
                mAdapter.notifyDataSetChanged()
            }

        }

        return root
    }
}
