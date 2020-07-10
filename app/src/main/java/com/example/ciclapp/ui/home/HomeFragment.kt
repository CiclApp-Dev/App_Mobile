package com.example.ciclapp.ui.home

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ciclapp.*
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        if(recargar == 1){
            activity?.finish()
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            recargar = 0
        }
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        root.botonModelo.setOnClickListener{
            (activity as MainActivity?)?.selector()
        }

        root.botoncito.setOnClickListener{
            val spinnerLoco = SpinnerDialog(activity, marcas, "Marca del telefono")

            spinnerLoco.setTitleColor(getResources().getColor(R.color.colorBlack))
            spinnerLoco.setSearchIconColor(getResources().getColor(R.color.colorBlack))
            spinnerLoco.setSearchTextColor(getResources().getColor(R.color.colorBlack))
            spinnerLoco.setItemColor(getResources().getColor(R.color.colorBlack))
            spinnerLoco.setItemDividerColor(getResources().getColor(R.color.colorBlack))
            spinnerLoco.setCloseColor(getResources().getColor(R.color.colorBlack))

            spinnerLoco.bindOnSpinerListener { item, position ->
                root.marcaSeleccionada.text = item
                root.modeloSeleccionado.text = "Seleccione"
            }
            spinnerLoco.showSpinerDialog()

        }

        root.BotonEscanear.setOnClickListener{
            val scanner = IntentIntegrator(activity)
            scanner.setPrompt("Coloque un código de barras o código QR en el interior del rectángulo del visor para escanear")
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }

        root.cargar_telefono_.setOnClickListener{
            if(root.editText5.text.toString() == "Chococrispi" || root.editText5.text.toString() == "Flama") {
                //val myIntent = Intent(activity, chococrispi_activity::class.java)
                //this@MainActivity.startActivity(myIntent)
            }else if(root.textView8.text.toString() != "Sin cargar"){
                val a = root.editText2.text.toString()
                val b = root.editText4.text.toString()
                val c = root.editText5.text.toString()
                val d = root.editText6.text.toString()
                if("," in a || "," in b || "," in c || "," in d){
                    Toast.makeText(activity, "Los textos no pueden contener coma", Toast.LENGTH_LONG).show()
                }else{
                    if (root.editText2.text.toString() == "" || root.editText6.text.toString() == ""  || root.marcaSeleccionada.text.toString() == "Seleccione" || root.modeloSeleccionado.text.toString() == "Seleccione"){
                        (activity as MainActivity?)?.mostrar_mensaje_error()
                    }else{
                        (activity as MainActivity?)?.enviar(0)
                    }
                }
            }else {
                Toast.makeText(activity, "No se ha escaneado ningun telefono", Toast.LENGTH_LONG).show()
            }
        }

         root.Boton_vender.setOnClickListener{
            if(root.textView8.text.toString() != "Sin cargar"){
                (activity as MainActivity?)?.enviar(1)
            }else{
                Toast.makeText(activity, "No se ha escaneado ningun IMEI.", Toast.LENGTH_LONG).show()
            }
        }

        root.CrazyButton.setOnClickListener {
            (activity as MainActivity?)?.limpiar(1)
        }

        root.CrazyButtonNO.setOnClickListener {
            (activity as MainActivity?)?.limpiar(2)
        }

        root.Boton_versiones.setOnClickListener{
            if(root.textView5.text != "Sin cargar" && root.textView5.text != "Sin asignar") {


                val id_enviar = root.textView8.text.toString()
                val devolucion = (activity as MainActivity?)?.enviar_mensaje("2,$id_enviar")
                cantidad = 0
                versiones = mutableListOf()

                for (item in devolucion!!.split("&")) {
                    cantidad = cantidad + 1
                    versiones.add(item.split(","))
                }

                if(devolucion != "Error"){
                    val myIntent = Intent(activity, Versiones::class.java)
                    startActivity(myIntent)
                }

            }else if(root.textView5.text == "Sin cargar"){
                Toast.makeText(activity, "No se ha escaneado ningun telefono", Toast.LENGTH_LONG).show()
            }else if(root.textView5.text == "Sin asignar"){
                Toast.makeText(activity, "Aun no hay versiones del telefono seleccionado", Toast.LENGTH_LONG).show()
            }
        }
        root.borrar_telefono_boton.setOnClickListener {
            (activity as MainActivity?)?.eliminar_telefono(root.textView8.text.toString())
        }
        return root
    }
}
