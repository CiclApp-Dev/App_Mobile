package com.example.ciclapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ciclapp.R
import com.example.ciclapp.versiones
import kotlinx.android.synthetic.main.activity_main.*


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_versiones, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)
        pageViewModel.text.observe(this, Observer<String> {
            textView.text = it
        })


        //Aca poner las cosas
        //Para encontrar un objeto --> val nombreVariable: TextView = root.findViewById(R.id.nombreElemento)

        val idLoco = (arguments?.getInt(ARG_SECTION_NUMBER)).toString()
        val id = idLoco.toInt()


        val revisor: TextView = root.findViewById(R.id.texto_revisor)
        val color: TextView = root.findViewById(R.id.texto_color)
        val porcentaje: TextView = root.findViewById(R.id.texto_porcentaje)
        val otros: TextView = root.findViewById(R.id.texto_otros)
        val ubicacion: TextView = root.findViewById(R.id.texto_ubicacion)
        val gb: TextView = root.findViewById(R.id.texto_gb)
        val estado: TextView = root.findViewById(R.id.texto_estado)
        val estetica: TextView = root.findViewById(R.id.texto_estetica)
        val bateria: TextView = root.findViewById(R.id.texto_bateria)

        val vidrioa: RadioButton = root.findViewById(R.id.radio_vidrioa)
        val vidriob: RadioButton = root.findViewById(R.id.radio_vidriob)
        val vidrioc: RadioButton = root.findViewById(R.id.radio_vidrioc)
        val vidriod: RadioButton = root.findViewById(R.id.radio_vidriod)

        val moduloa: RadioButton = root.findViewById(R.id.radio_moduloa)
        val modulob: RadioButton = root.findViewById(R.id.radio_modulob)
        val moduloc: RadioButton = root.findViewById(R.id.radio_moduloc)
        val modulod: RadioButton = root.findViewById(R.id.radio_modulod)
        val moduloe: RadioButton = root.findViewById(R.id.radio_moduloe)

        val trasa: RadioButton = root.findViewById(R.id.radio_trasa)
        val trasb: RadioButton = root.findViewById(R.id.radio_trasb)
        val trasc: RadioButton = root.findViewById(R.id.radio_trasc)
        val trasd: RadioButton = root.findViewById(R.id.radio_trasd)

        val wifi: CheckBox = root.findViewById(R.id.check_wifi)
        val bluetooth: CheckBox = root.findViewById(R.id.check_bluetooth)
        val parlantet: CheckBox = root.findViewById(R.id.check_parlantet)
        val auricular: CheckBox = root.findViewById(R.id.check_auricular)
        val portasim: CheckBox = root.findViewById(R.id.check_portasim)
        val parlanted: CheckBox = root.findViewById(R.id.check_parlanted)
        val microfono: CheckBox = root.findViewById(R.id.check_microfono)
        val liberar: CheckBox = root.findViewById(R.id.check_liberar)
        val sensor: CheckBox = root.findViewById(R.id.check_sensor)
        val carcasa: CheckBox = root.findViewById(R.id.check_carcasa)
        val pincarga: CheckBox = root.findViewById(R.id.check_pincarga)
        val camarad: CheckBox = root.findViewById(R.id.check_camarad)
        val botones: CheckBox = root.findViewById(R.id.check_botones)
        val tactil: CheckBox = root.findViewById(R.id.check_tactil)
        val camarat: CheckBox = root.findViewById(R.id.check_camarat)




        val actual = versiones[id-1]

        revisor.text = actual[5]
        color.text = actual[7]
        porcentaje.text = actual[18]
        otros.text = actual[24]
        ubicacion.text = actual[26]
        gb.text = actual[6]
        estado.text = actual[25]
        estetica.text = actual[8]
        bateria.text = actual[17]


        if(actual[21] == "OK" || actual[21] == "0"){
            vidrioa.setChecked(true)
        }else if(actual[21]=="A" || actual[21] == "1"){
            vidriob.setChecked(true)
        }else if(actual[21]=="B" || actual[21] == "2"){
            vidrioc.setChecked(true)
        }else if(actual[21]=="NO" || actual[21] == "3"){
            vidriod.setChecked(true)
        }

        if(actual[22] == "OK" || actual[22] == "0"){
            moduloa.setChecked(true)
        }else if(actual[22]=="A" || actual[22] == "1"){
            modulob.setChecked(true)
        }else if(actual[22]=="B" || actual[22] == "2"){
            moduloc.setChecked(true)
        }else if(actual[22]=="C" || actual[22] == "3"){
            modulod.setChecked(true)
        }else if(actual[22]=="NO" || actual[22] == "4"){
            moduloe.setChecked(true)
        }

        if(actual[23] == "A" || actual[23] == "0"){
            trasa.setChecked(true)
        }else if(actual[23]=="B" || actual[23] == "1"){
            trasb.setChecked(true)
        }else if(actual[23]=="C" || actual[23] == "2"){
            trasc.setChecked(true)
        }else if(actual[23]=="D" || actual[23] == "3"){
            trasd.setChecked(true)
        }


        if(actual[9]=="1" || actual[9]=="-"){
            carcasa.setChecked(true)
        }

        if(actual[10]=="1" || actual[10]=="-"){
            camarat.setChecked(true)
        }

        if(actual[11]=="1" || actual[11]=="-"){
            camarad.setChecked(true)
        }

        if(actual[12]=="1" || actual[12]=="-"){
            pincarga.setChecked(true)
        }

        if(actual[13]=="1" || actual[13]=="-"){
            auricular.setChecked(true)
        }

        if(actual[14]=="1" || actual[14]=="-"){
            parlanted.setChecked(true)
        }

        if(actual[15]=="1" || actual[15]=="-"){
            parlantet.setChecked(true)
        }

        if(actual[16]=="1" || actual[16]=="-"){
            sensor.setChecked(true)
        }

        if(actual[19]=="1" || actual[19]=="-"){
            wifi.setChecked(true)
        }

        if(actual[20]=="1" || actual[20]=="-"){
            bluetooth.setChecked(true)
        }

        if(actual[27]=="1" || actual[27]=="-"){
            liberar.setChecked(true)
        }
        if(actual[28]=="1" || actual[28]=="-"){
            portasim.setChecked(true)
        }
        if(actual[29]=="1" || actual[29]=="-"){
            microfono.setChecked(true)
        }
        if(actual[30]=="1" || actual[30]=="-"){
            botones.setChecked(true)
        }
        if(actual[31]=="1" || actual[31]=="-"){
            tactil.setChecked(true)
        }





        //Hasta aca poner las cosas
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}