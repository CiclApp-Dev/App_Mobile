package com.example.ciclapp

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

var telefono_actual = arrayOf<String>()
var versiones = mutableListOf<List<String>>()
var cantidad = 1
var retornos_hilo = ""
var roto = "0"
var ip_servidor = "18.216.97.211"
val marcas = arrayListOf("Samsung","Apple","Motorola","Sony","LG","Huawei","Alcatel","Otros")
val samsung = arrayListOf("Ativ S", "Core Plus", "Galaxy A10", "Galaxy A10e", "Galaxy A10s", "Galaxy A2 Core", "Galaxy A20", "Galaxy A20e", "Galaxy A20s", "Galaxy A3 (2016)", "Galaxy A3 (2017)", "Galaxy A3", "Galaxy A30", "Galaxy A30s", "Galaxy A40", "Galaxy A40s", "Galaxy A5 (2016)", "Galaxy A5 (2017)", "Galaxy A5", "Galaxy A50", "Galaxy A50s", "Galaxy A51", "Galaxy A6 (2018)", "Galaxy A6 Plus (2018)", "Galaxy A60", "Galaxy A6s", "Galaxy A7 (2016)", "Galaxy A7 (2017)", "Galaxy A7 (2018)", "Galaxy A7", "Galaxy A70", "Galaxy A70s", "Galaxy A71", "Galaxy A8 (2016)", "Galaxy A8 (2018)", "Galaxy A8 Star", "Galaxy A8", "Galaxy A8+", "Galaxy A80", "Galaxy A8s", "Galaxy A9 (2016)", "Galaxy A9 (2018)", "Galaxy A9 Pro (2019)", "Galaxy A9 Pro", "Galaxy A9 Star Lite", "Galaxy A9 Star", "Galaxy A90 5G", "Galaxy Ace 2", "Galaxy Ace 3", "Galaxy Ace 4", "Galaxy Ace Duos", "Galaxy Ace Plus", "Galaxy Ace", "Galaxy Alpha", "Galaxy C5 Pro", "Galaxy C5", "Galaxy C7 2017", "Galaxy C7 Pro", "Galaxy C7", "Galaxy C8", "Galaxy C9", "Galaxy Core 2", "Galaxy Core LTE", "Galaxy Core Prime", "Galaxy Core", "Galaxy E5", "Galaxy E7", "Galaxy Express 2", "Galaxy Express", "Galaxy Fold", "Galaxy Grand 2", "Galaxy Grand I9082", "Galaxy Grand Neo Plus", "Galaxy Grand Neo", "Galaxy Grand Prime", "Galaxy Grand Prime+", "Galaxy Grand", "Galaxy J1 (2016)", "Galaxy J1 mini Prime", "Galaxy J1 mini", "Galaxy J1", "Galaxy J2 (2018)", "Galaxy J2 Ace", "Galaxy J2 Core", "Galaxy J2 Prime", "Galaxy J2 Pro", "Galaxy J2", "Galaxy J3 (2016)", "Galaxy J3 (2017)", "Galaxy J3 Pro", "Galaxy J3", "Galaxy J4 (2018)", "Galaxy J4 Core", "Galaxy J4+", "Galaxy J5 (2016)", "Galaxy J5 (2017)", "Galaxy J5 Prime", "Galaxy J5", "Galaxy J6 (2018)", "Galaxy J6+", "Galaxy J7 (2016)", "Galaxy J7 (2017)", "Galaxy J7 (2018)", "Galaxy J7 Max", "Galaxy J7 Nxt", "Galaxy J7 Prime 2", "Galaxy J7 Prime", "Galaxy J7", "Galaxy J7+", "Galaxy J8", "Galaxy Jean2", "Galaxy K zoom", "Galaxy M10", "Galaxy M10S", "Galaxy M20", "Galaxy M30", "Galaxy M30s", "Galaxy M40", "Galaxy Mega 2", "Galaxy Mega 5.8", "Galaxy Mega 6.3", "Galaxy Note 10 Lite", "Galaxy Note 10", "Galaxy Note 3 N9000", "Galaxy Note 3 N9005 LTE", "Galaxy Note 3 Neo LTE+", "Galaxy Note 4", "Galaxy Note 5", "Galaxy Note 7", "Galaxy Note 8", "Galaxy Note 9", "Galaxy Note Edge", "Galaxy Note FE", "Galaxy Note10+", "Galaxy On Max", "Galaxy On Nxt", "Galaxy On5 2016", "Galaxy On5", "Galaxy On6", "Galaxy On7 Prime (2018)", "Galaxy On7", "Galaxy On8", "Galaxy S Luxury Edition", "Galaxy S10 5G", "Galaxy S10 Lite", "Galaxy S10 Plus", "Galaxy S10", "Galaxy S10e", "Galaxy S2 Plus", "Galaxy S20 Ultra", "Galaxy S20+", "Galaxy S3 LTE I9305", "Galaxy S3 Mini VE", "Galaxy S3 Neo", "Galaxy S3 mini", "Galaxy S3", "Galaxy S4 Active", "Galaxy S4 Duos I9502", "Galaxy S4 I9500", "Galaxy S4 I9505", "Galaxy S4 Mini I9195 LTE", "Galaxy S4 mini I9190", "Galaxy S4 mini I9192 Duos", "Galaxy S4 zoom", "Galaxy S5 Duos", "Galaxy S5 Mini", "Galaxy S5 Octa core", "Galaxy S5 Plus", "Galaxy S5", "Galaxy S6 Active", "Galaxy S6 Edge", "Galaxy S6 edge+", "Galaxy S6", "Galaxy S7 Active", "Galaxy S7 Edge", "Galaxy S7", "Galaxy S8 Active", "Galaxy S8", "Galaxy S8+", "Galaxy S9", "Galaxy S9+", "Galaxy Trend II", "Galaxy Trend Lite", "Galaxy Trend Plus", "Galaxy W20 5G", "Galaxy Wide4", "Galaxy Xcover 2", "Galaxy Xcover 3", "Galaxy Xcover 4", "Galaxy Xcover 4s", "Galaxy Young 2", "Galaxy Young", "Z4","Galaxy S20")
val apple = arrayListOf("iPhone 11", "iPhone 11 Pro", "iPhone 11 Pro Max", "iPhone Xr", "iPhone Xs", "iPhone Xs Max", "iPhone X", "iPhone 8", "iPhone 8 Plus", "iPhone 7", "iPhone 7 Plus", "iPhone SE", "iPhone 6s", "iPhone 6s Plus", "iPhone 6", "iPhone 6 Plus", "iPhone 5s", "iPhone 5c", "iPhone 5", "iPhone 4S", "iPhone 4")
val motorola = arrayListOf("One Hyper", "Razr 2019", "Moto E6 Play", "Moto G8 Plus", "Moto G8 Play", "One Macro", "Moto E6S", "Moto E6 Plus", "One Zoom", "One Action", "P50", "Moto Z4", "One Vision", "Moto G7 Play", "Moto G7 Power", "Moto G7", "Moto G7 Plus", "P30 Play", "One", "P30 Note", "P30", "Moto Z3", "Moto Z3 Play", "Moto E5 Play", "Moto G6", "Moto G6 Play", "Moto G6 Plus", "Moto E5 Plus", "Moto E5", "Moto Z 2018", "Moto Green Pomelo", "Moto Z2 Force Edition", "Moto X4", "Moto G5s Plus", "Moto G5s", "Moto Z2 Play", "Moto C Plus", "Moto C", "Moto E4", "Moto E4 Plus", "Moto M", "Moto E3", "Moto G4 Play", "Moto G4 Plus", "Moto G4", "Moto X Force", "Droid Turbo 2", "Moto G (3rd gen)", "Moto X Style", "Moto X Play", "Moto E", "Moto E (2nd Gen) XT1527", "Moto E (2nd Gen)", "Nexus 6", "Moto G 2014", "Moto X 2014", "Añadir a comparativa", "Moto G 4G", "Moto G", "Moto X")
val sony = arrayListOf("Xperia 1 Professional Edition", "Xperia 5", "Xperia 10", "Xperia 10 Plus", "Xperia 1", "Xperia L3", "Xperia XZ3", "Xperia XA2 Plus", "Xperia XZ2 Premium", "Xperia XZ2", "Xperia XZ2 Compact", "Xperia XA2", "Xperia L2", "Xperia XA2 Ultra", "Xperia R1", "Xperia R1 Plus", "Xperia XA1 Plus", "Xperia XZ1", "Xperia XZ1 Compact", "Xperia L1", "Xperia XZs", "Xperia XA1", "Xperia XZ Premium", "Xperia XA1 Ultra", "Xperia XZ", "Xperia X Compact", "Xperia E5", "Xperia XA Ultra", "Xperia XA", "Xperia X", "Xperia X Performance", "Xperia Z5 Compact", "Xperia Z5 Premium", "Xperia Z5", "Xperia C5 Ultra", "Xperia M5", "Xperia Z3+", "Xperia C4", "Xperia M4 Aqua", "Xperia E4", "Xperia E4g Dual", "Xperia E4G", "Xperia Z3", "Xperia Z3 Compact", "Xperia E3", "Xperia C3", "Xperia Z2a", "Xperia T3 4G", "Xperia M2", "Xperia Z2", "Xperia Z1 Compact", "Xperia E1", "Xperia T2 Ultra", "Xperia Z1s", "Xperia Z1", "Xperia M", "Xperia C", "Xperia Z Ultra", "Xperia ZR", "Xperia L", "Xperia SP", "Xperia Z", "Xperia ZL", "Xperia T")
val lg = arrayListOf("K50S", "G8X ThinQ", "K40S", "K20", "K30", "K12 Prime", "Stylo 5", "W10", "W30", "W30 Pro", "Risio 3", "X6", "K8S", "K12+", "K40", "G8 ThinQ", "V50 Thinq 5G", "G8s ThinkQ", "K50", "K12 Max", "Q9 One", "Q60", "Q9", "V40 ThinQ", "G7 One", "G7 Fit", "Q8 (2018)", "G7+ ThinQ", "Signature Edition 2018", "Q Note+", "X Power 3", "X2 (2018)", "Stylo 4 Plus", "Q Stylus+", "Q Stylus", "V35 ThinQ", "Q7", "Q7+", "K9", "G7 ThinQ", "K10 Pro", "K11", "K8 (2018)", "V30S", "K7i", "V30", "Q6a", "Q6", "Q8", "K10 Power", "X Power 2", "G6", "K10 (2017)", "K8 (2017)", "Aristo", "Stylus 3", "K4 (2017)", "V20", "X Fast", "X Style", "X mach", "K3", "X Power", "G5 SE", "X cam", "K5", "X screen", "K8", "Stylus 2", "G5", "K10", "K4", "K7", "Wine Smart", "V10", "Nexus 5X", "G4s Beat", "Bello II", "G4c", "G4", "G4 Stylus", "Spirit", "Leon", "Magna", "G Flex 2", "F60", "G3 Stylus", "L Fino", "L Bello", "L60", "G3 S", "L50", "L65", "G3", "L80", "Optimus Exceed 2", "G2 Mini LTE", "G2 Mini", "L90", "L90 Dual", "L70", "L70 Dual", "L40", "G Pro 2", "Optimus L1 II Tri", "F70", "Optimus F3Q", "GX", "G Flex", "Fireweb", "G Pro Lite", "Optimus L2 II", "Google Nexus 5", "G2", "Optimus L9 II", "Enact", "Optimus F6", "Zero", "Optimus Zone", "Optimus L4 II", "Optimus F3", "Optimus L4 II Dual", "Lucid 2", "Optimus L7 II", "Optimus L7 II Dual", "Optimus L3 II", "Optimus L5 II Dual", "Optimus L5 II", "Optimus L3 II Dual", "Optimus G Pro", "Optimus F5", "Optimus F7", "Nexus 4", "Optimus G", "Optimus L7", "Optimus L5", "Optimus L3", "Optimus 4X HD P880", "Optimus LTE")
val huawei = arrayListOf("Y6s", "Nova 5T Pro", "P smart Pro 2019", "nova 6 SE", "Nova 6", "Enjoy 10S", "Honor V30", "Honor V30 Pro", "Y9s", "Honor 20 Youth", "Enjoy 10", "nova 5z", "Nova 5T", "Mate 30", "Mate 30 Pro", "Honor Play 3", "Honor Play 3e", "Enjoy 10 Plus", "Honor 20S", "Honor 9x", "Honor 9x Pro", "Mate 30 Lite", "nova 5i Pro", "Honor Play 8", "P20 Lite 2019", "nova 5", "nova 5 Pro", "nova 5i", "Maimang 8", "Honor 20 Pro", "Honor 20", "Mate 20X 5G", "Y9 Prime 2019", "P Smart Z", "Honor 20 Lite", "Y7 Pro 2019", "Honor 8s", "Y5 2019", "Honor 20i", "Y6 Prime 2019", "P30 Lite", "P30 Pro", "P30", "Enjoy 9e", "Enjoy 9s", "Honor 10i", "nova 4e", "P smart+ 2019", "Y6 2019", "Mate X", "Y6 Pro 2019", "Y7 Prime 2019", "Honor Play 8A", "P Smart 2019", "Honor View 20", "Y Max", "nova 4", "Enjoy 9", "Honor 10 Lite", "Honor Magic 2", "Mate 20 Pro", "Mate 20 RS Porsche Design", "Mate 20", "Mate 20X", "Enjoy Max", "Enjoy 9 Plus", "Honor 8C", "Y9 (2019)", "Maimang 7", "Honor 8x", "Honor 8x Max", "Enjoy 8e Lite", "Mate 20 Lite", "P smart+", "Honor Note 10", "Honor 9N", "nova 3i", "Nova 3", "Honor 9i", "Honor Play", "Honor Play 7", "Y5 2018", "Y3 2018", "Honor 7S", "Y6 2018", "Honor 10", "Mate RS", "Enjoy 8e", "Honor 7A", "Y7 Prime 2018", "Enjoy 8", "Enjoy 8 Plus", "P20 Lite", "Y9 (2018)", "P20 Pro", "P20", "nova 3e", "Y7 Pro 2018", "nova 2 Lite", "Honor 7C", "P Smart", "Nova 2s", "Enjoy 7s", "Honor 9 Lite", "Honor V10", "Honor 6C Pro", "Honor Holly 4", "Honor 7x", "Mate 10", "Mate 10 Pro", "Mate 10 Lite", "Honor Holly 4 Plus", "Maimang 6", "nova 2i", "Honor V9 Play", "Honor 6 Play", "P9 Lite mini", "Y6 2017", "Enjoy 7", "Honor Holly 3+", "Y3 2017", "Honor 9", "Y7 Prime", "Nova 2", "Nova 2 Plus", "Nova Young", "Y6 Pro 2017", "Honor 6A", "Y7", "Nova Youth", "Nova Smart", "Enjoy 7 Plus", "Honor Bee 2", "Honor 9i India", "Honor 6C", "P10 Lite", "Honor 8 Pro", "P10", "P10 Plus", "Honor V9", "Nova Lite", "GR5 2017", "Honor 8 Lite", "P8 Lite 2017", "P9 Lite 2017", "P8 Lite Smart", "Honor Magic", "Honor 8 Smart", "Enjoy 6s", "Mate 9 Pro", "Mate 9", "Mate 9 Lite", "Enjoy 6", "Honor 6X", "Honor Holly 3", "Nova", "Nova Plus", "Y6II Compact", "Honor 5", "Honor Note 8", "G9 Plus", "Maimang 5", "Honor 8", "Y6 II", "Y3 II", "GT3", "Honor 5A", "G9 Lite", "Ascend Y5 II", "Honor V8", "Honor 5C", "Honor 4C Pro", "P9", "P9 Plus", "GR5", "P9 Lite", "Honor Holly 2 Plus", "Y6 Pro", "Maimang 4", "Enjoy 5S", "G629", "Ascend G7 Plus", "Mate 8", "Honor Shot X", "Enjoy 5", "Y6", "Honor 5X", "Nexus 6P", "GX1s", "GX8", "Mate S", "Honor 7i", "GR3", "Y6+", "Honor 4A Play", "Ascend Y5", "Honor 7", "Honor Bee", "P8 Max", "G Play mini", "Y625", "P8", "P8 Lite", "Y3", "Y635", "MediaPad Honor X2", "Honor 4C Play", "Ascend Y540", "Honor 6 Plus", "G620", "Honor 4X Play", "Honor 4X", "Honor Holly", "Ascend G535", "Ascend Mate7", "Ascend Y550", "Ascend G620S", "Ascend G7", "Honor 4 Play", "C199", "Honor 3C Play Edition", "Honor 6", "Honor 3C 4G", "Ascend P7", "Honor 3X Pro", "Ascend P7 mini", "Ascend G630", "Ascend Y330", "Ascend Y600", "MediaPad Honor X1", "Ascend G6", "Ascend G730", "Ascend Y530", "Ascend G6 4G", "B199", "Ascend P6 S", "Ascend Mate 2 4G", "Honor 3C", "Honor 3X", "Ascend G740", "Ascend G700", "Honor 3", "Ascend G525", "G610s", "Ascend P6", "Ascend Y300", "Ascend P2", "Ascend P1 LTE", "Ascend G615", "Ascend Mate", "Ascend G510", "Ascend D2", "Honor 2")
val alcatel = arrayListOf("3", "1C", "3V", "3X", "3C", "Pixi 4 Plus Power", "A7 Xl", "A3 Plus 3G", "Idol 5", "A7", "U5 HD", "Idol 5S", "A3 XL", "A3", "A5 LED", "Flash (2017)", "U5", "Pixi 4 (5)", "Idol 4S", "Pop 4+", "Pop 4 (6)", "Flash Plus 2", "OneTouch Flash 2", "Pixi 4 (4)", "Pixi 4 (6)", "Idol 4", "Pop 4", "Pop 4S", "OneTouch Pop Up", "OneTouch Pop 3 (5.5)", "OneTouch Go Play", "OneTouch Pop 3 (5)", "OneTouch Pop Star 3G", "OneTouch Pop Star 4G", "Shine Lite", "OneTouch Pixi First", "OneTouch Idol 3 (4.7)", "Pixi 3", "OneTouch Idol 2 Mini", "OneTouch Idol 2", "OneTouch Fire E", "OneTouch Pop C5", "Onetouch Hero 2", "OneTouch Pop 2 (5)", "OneTouch Pop 2 (4)", "OneTouch Pop 2 (4.5)", "OneTouch Pop D5", "OneTouch Idol 3", "OneTouch Pop C2", "OneTouch Pop C1", "OneTouch Pop S7", "OneTouch Pop S3", "OneTouch Idol X+", "OneTouch Pop C9", "OneTouch Pop C3", "OneTouch Idol Alpha", "OneTouch Pop C7", "OneTouch Star", "OneTouch Idol Ultra")
val otros = arrayListOf("Otros")
var usuario = ""


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //Aca empiezan las funciones propias
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Escaneo cancelado", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Escaneado: " + result.contents, Toast.LENGTH_LONG).show()
                    Thread(Runnable {
                        try {
                            val s = Socket(ip_servidor, 42069)
                            //val caca = (MensajeEscrito.text).toString()
                            s.outputStream.write(result.contents.toByteArray())
                            val mensajito = BufferedReader(InputStreamReader(s.getInputStream()))
                            val ServerMessage = mensajito.readLine()
                            s.close()
                            runOnUiThread(object:Runnable{
                                public override fun run() {
                                    if(ServerMessage == "NewPhone"){
                                        textView5.text = "Sin asignar"
                                        textView8.text = result.contents
                                        limpiar()
                                    } else {
                                        limpiar()
                                        cargar(ServerMessage)
                                    }
                                }
                            })
                        }catch (e: Exception) {


                            runOnUiThread(object:Runnable{
                                public override fun run() {
                                    Toast.makeText(this@MainActivity, "No hay conexion", Toast.LENGTH_LONG).show()
                                }
                            })


                        }
                    }).start()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    fun selector(){
        if (marcaSeleccionada.text == "Samsung") {
            selectorLoco(marcaSeleccionada.text.toString(), samsung)
        } else if (marcaSeleccionada.text == "Apple") {
            selectorLoco(marcaSeleccionada.text.toString(), apple)
        } else if (marcaSeleccionada.text == "Motorola") {
            selectorLoco(marcaSeleccionada.text.toString(), motorola)
        } else if (marcaSeleccionada.text == "Sony") {
            selectorLoco(marcaSeleccionada.text.toString(), sony)
        } else if (marcaSeleccionada.text == "LG") {
            selectorLoco(marcaSeleccionada.text.toString(), lg)
        } else if (marcaSeleccionada.text == "Huawei") {
            selectorLoco(marcaSeleccionada.text.toString(), huawei)
        } else if (marcaSeleccionada.text == "Alcatel") {
            selectorLoco(marcaSeleccionada.text.toString(), alcatel)
        } else if (marcaSeleccionada.text == "Otros") {
            selectorLoco(marcaSeleccionada.text.toString(), otros)
        }else{
            Toast.makeText(this, "No se ha seleccionado la marca", Toast.LENGTH_LONG).show()
        }
    }

    fun limpiar(){
        marcaSeleccionada.text = "Seleccione"
        modeloSeleccionado.text = "Seleccione"
        editText2.setText("")

        editText4.setText("")
        editText5.setText("")
        editText6.setText("")
        radioGroup2.clearCheck()
        radioGroup3.clearCheck()
        radioGroup4.clearCheck()
        radioGroup5.clearCheck()
        radioGroup6.clearCheck()
        check_wifi.setChecked(false)
        check_bluetooth.setChecked(false)
        check_parlantet.setChecked(false)
        check_auricular.setChecked(false)
        check_portasim.setChecked(false)
        check_parlanted.setChecked(false)
        check_microfono.setChecked(false)
        check_liberar.setChecked(false)
        check_sensor.setChecked(false)
        check_carcasa.setChecked(false)
        check_pincarga.setChecked(false)
        check_camarad.setChecked(false)
        check_botones.setChecked(false)
        check_tactil.setChecked(false)
        check_camarat.setChecked(false)

        spinner_gb.setSelection(0)
        spinner3.setSelection(0)


    }

    fun enviar(){
        Thread(Runnable {
            try {
                var estetica = ""
                if(radioButton7.isChecked()){
                    estetica = "A"
                }else if (radioButton8.isChecked()){
                    estetica = "B"
                }else {
                    estetica = "C"
                }

                var carcasa = "X"
                if(check_carcasa.isChecked()){
                    carcasa = "-"
                }
                var camarat = "X"
                if(check_camarat.isChecked()){
                    camarat = "-"
                }
                var camarad = "X"
                if(check_camarad.isChecked()) {
                    camarad = "-"
                }
                var pin = "X"
                if(check_pincarga.isChecked()){
                    pin = "-"
                }
                var auriculares = "X"
                if(check_auricular.isChecked()){
                    auriculares = "-"
                }
                var parlantef = "X"
                if(check_parlanted.isChecked()){
                    parlantef = "-"
                }
                var parlantet = "X"
                if(check_parlantet.isChecked()){
                    parlantet = "-"
                }
                var sensorprox = "X"
                if(check_sensor.isChecked()){
                    sensorprox = "-"
                }
                var bateria = "X"
                if(radioButton4.isChecked()){
                    bateria = "-"
                }
                var bateriaporcentaje = editText4.text.toString()
                var wifi = "X"
                if(check_wifi.isChecked()){
                    wifi="-"
                }
                var bluetooth = "X"
                if(check_bluetooth.isChecked()){
                    bluetooth = "-"
                }
                var liberar = "X"
                if(check_liberar.isChecked()){
                    liberar = "-"
                }
                var portasim = "X"
                if(check_portasim.isChecked()){
                    portasim = "-"
                }
                var micro = "X"
                if(check_microfono.isChecked()){
                    micro = "-"
                }
                var botones = "X"
                if(check_botones.isChecked()){
                    botones ="-"
                }
                var tactil = "X"
                if(check_tactil.isChecked()){
                    tactil="-"
                }
                var vidrio = ""
                if(radioButton10.isChecked()){
                    vidrio = "OK"
                }else if (radioButton11.isChecked()){
                    vidrio = "A"
                }else if (radioButton12.isChecked()){
                    vidrio = "B"
                }else if (radioButton13.isChecked()){
                    vidrio = "NO"
                }

                var modulo = ""
                if(radioButton14.isChecked()){
                    modulo = "OK"
                }else if(radioButton15.isChecked()){
                    modulo = "A"
                }else if(radioButton16.isChecked()){
                    modulo = "B"
                }else if(radioButton17.isChecked()){
                    modulo = "C"
                }else if(radioButton18.isChecked()){
                    modulo = "NO"
                }

                var traslucido = ""
                if(radioButton19.isChecked()){
                    traslucido = "A"
                }else if(radioButton20.isChecked()){
                    traslucido = "B"
                }else if(radioButton21.isChecked()){
                    traslucido = "C"
                }else if(radioButton13.isChecked()){
                    traslucido = "D"
                }

                val listaDeLaMuerte = textView8.text.toString() + "," + marcaSeleccionada.text.toString() + "," + modeloSeleccionado.text.toString()+ "," + "00/00" + "," + spinner.getSelectedItem().toString() + "," + spinner_gb.getSelectedItem().toString() + "," + editText2.text.toString() + "," + estetica + "," + carcasa + "," + camarat + "," + camarad + "," + pin  + "," + auriculares + "," + parlantef + "," + parlantet + "," + sensorprox + "," + bateria + "," + bateriaporcentaje + "," + wifi + "," + bluetooth + "," + vidrio + "," + modulo + "," + traslucido + "," + editText5.text.toString() + "," + spinner3.getSelectedItem().toString() + "," + editText6.text.toString() + "," + liberar + "," + portasim + "," + micro + "," + botones + "," + tactil

                val s = Socket("18.216.97.211", 42069)
                s.outputStream.write(listaDeLaMuerte.toByteArray())
                val mensajito = BufferedReader(InputStreamReader(s.getInputStream()))
                val ServerMessage = mensajito.readLine()
                s.close()
                if(ServerMessage=="Ok"){

                    runOnUiThread(object:Runnable{
                        public override fun run() {
                            Toast.makeText(this@MainActivity, "Telefono cargado con exito", Toast.LENGTH_LONG).show()
                            limpiar()
                            textView8.text = "Sin cargar"
                            textView5.text = "Sin cargar"
                        }
                    })

                }else if(ServerMessage!="Ok"){
                    runOnUiThread(object:Runnable{
                        public override fun run() {
                            Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()

                        }
                    })

                }
            }catch (e: Exception) {
                runOnUiThread(object:Runnable{
                    public override fun run() {
                        Toast.makeText(this@MainActivity, "No hay conexion", Toast.LENGTH_LONG).show()
                    }
                })


            }
        }).start()
    }

    fun cargar(listaLoca: String) {
        val listarda = listaLoca.split(",")
        textView5.text = listarda[0]
        textView8.text = listarda[1]
        marcaSeleccionada.text = listarda[2]
        modeloSeleccionado.text = listarda[3]

        editText2.setText(listarda[7])
        editText4.setText(listarda[18])
        editText5.setText(listarda[24])
        editText6.setText(listarda[26])

        if(listarda[6]=="Seleccione"){
            spinner_gb.setSelection(0)
        }else if(listarda[6]=="0,5"){
            spinner_gb.setSelection(1)
        }else if(listarda[6]=="1"){
            spinner_gb.setSelection(2)
        }else if(listarda[6]=="2"){
            spinner_gb.setSelection(3)
        }else if(listarda[6]=="4"){
            spinner_gb.setSelection(4)
        }else if(listarda[6]=="8"){
            spinner_gb.setSelection(5)
        }else if(listarda[6]=="16"){
            spinner_gb.setSelection(6)
        }else if(listarda[6]=="32"){
            spinner_gb.setSelection(7)
        }else if(listarda[6]=="64"){
            spinner_gb.setSelection(8)
        }else if(listarda[6]=="128"){
            spinner_gb.setSelection(9)
        }else if(listarda[6]=="256"){
            spinner_gb.setSelection(10)
        }else if(listarda[6]=="512"){
            spinner_gb.setSelection(11)
        }

        if(listarda[25]=="Seleccione"){
            spinner3.setSelection(0)
        }else if(listarda[25]=="Listo para vender"){
            spinner3.setSelection(1)
        }else if(listarda[25]=="Reparar"){
            spinner3.setSelection(2)
        }else if(listarda[25]=="Repuesto"){
            spinner3.setSelection(3)
        }else if(listarda[25]=="Liberar"){
            spinner3.setSelection(4)
        }else if(listarda[25]=="Vendido"){
            spinner3.setSelection(5)
        }



        if(listarda[8] == "A" || listarda[8] == "0"){
            radioButton7.setChecked(true)
        }else if(listarda[8]=="B" || listarda[8] == "1"){
            radioButton8.setChecked(true)
        }else if(listarda[8]=="C" || listarda[8] == "2"){
            radioButton9.setChecked(true)
        }

        if(listarda[21] == "OK" || listarda[21] == "0"){
            radioButton10.setChecked(true)
        }else if(listarda[21]=="A" || listarda[21] == "1"){
            radioButton11.setChecked(true)
        }else if(listarda[21]=="B" || listarda[21] == "2"){
            radioButton12.setChecked(true)
        }else if(listarda[21]=="NO" || listarda[21] == "3"){
            radioButton13.setChecked(true)
        }

        if(listarda[17] == "-" || listarda[17] == "1"){
            radioButton4.setChecked(true)
        }else if(listarda[17]=="X" || listarda[17] == "0"){
            radioButton5.setChecked(true)
        }

        if(listarda[22] == "OK" || listarda[22] == "0"){
            radioButton14.setChecked(true)
        }else if(listarda[22]=="A" || listarda[22] == "1"){
            radioButton15.setChecked(true)
        }else if(listarda[22]=="B" || listarda[22] == "2"){
            radioButton16.setChecked(true)
        }else if(listarda[22]=="C" || listarda[22] == "3"){
            radioButton17.setChecked(true)
        }else if(listarda[22]=="NO" || listarda[22] == "4"){
            radioButton18.setChecked(true)
        }

        if(listarda[23] == "A" || listarda[23] == "0"){
            radioButton19.setChecked(true)
        }else if(listarda[23]=="B" || listarda[23] == "1"){
            radioButton20.setChecked(true)
        }else if(listarda[23]=="C" || listarda[23] == "2"){
            radioButton21.setChecked(true)
        }else if(listarda[23]=="D" || listarda[23] == "3"){
            radioButton23.setChecked(true)
        }


        if(listarda[9]=="1" || listarda[9]=="-"){
            check_carcasa.setChecked(true)
        }

        if(listarda[10]=="1" || listarda[10]=="-"){
            check_camarat.setChecked(true)
        }

        if(listarda[11]=="1" || listarda[11]=="-"){
            check_camarad.setChecked(true)
        }

        if(listarda[12]=="1" || listarda[12]=="-"){
            check_pincarga.setChecked(true)
        }

        if(listarda[13]=="1" || listarda[13]=="-"){
            check_auricular.setChecked(true)
        }

        if(listarda[14]=="1" || listarda[14]=="-"){
            check_parlanted.setChecked(true)
        }

        if(listarda[15]=="1" || listarda[15]=="-"){
            check_parlantet.setChecked(true)
        }

        if(listarda[16]=="1" || listarda[16]=="-"){
            check_sensor.setChecked(true)
        }

        if(listarda[19]=="1" || listarda[19]=="-"){
            check_wifi.setChecked(true)
        }

        if(listarda[20]=="1" || listarda[20]=="-"){
            check_bluetooth.setChecked(true)
        }

        if(listarda[27]=="1" || listarda[27]=="-"){
            check_liberar.setChecked(true)
        }
        if(listarda[28]=="1" || listarda[28]=="-"){
            check_portasim.setChecked(true)
        }
        if(listarda[29]=="1" || listarda[29]=="-"){
            check_microfono.setChecked(true)
        }
        if(listarda[30]=="1" || listarda[30]=="-"){
            check_botones.setChecked(true)
        }
        if(listarda[31]=="1" || listarda[31]=="-"){
            check_tactil.setChecked(true)
        }

    }

    fun enviar_mensaje(mensaje: String){

        var hilo = Thread(Runnable {
            try {
                val s = Socket("18.216.97.211", 42069)
                s.outputStream.write(mensaje.toByteArray())
                val mensajito = BufferedReader(InputStreamReader(s.getInputStream()))
                retornos_hilo = mensajito.readLine()
                s.close()
            }catch (e: Exception) {
                roto = "1"
            }
        })
        hilo.start()
        hilo.join()

    }

    fun selectorLoco(titulo: String, conjunto: ArrayList<String>){
        val spinnerLoco = SpinnerDialog(this, conjunto, titulo)

        spinnerLoco.setTitleColor(getResources().getColor(R.color.colorBlack))
        spinnerLoco.setSearchIconColor(getResources().getColor(R.color.colorBlack))
        spinnerLoco.setSearchTextColor(getResources().getColor(R.color.colorBlack))
        spinnerLoco.setItemColor(getResources().getColor(R.color.colorBlack))
        spinnerLoco.setItemDividerColor(getResources().getColor(R.color.colorBlack))
        spinnerLoco.setCloseColor(getResources().getColor(R.color.colorBlack))

        spinnerLoco.bindOnSpinerListener { item, position ->
            modeloSeleccionado.text = item
        }
        spinnerLoco.showSpinerDialog()
    }

    fun mostrar_mensaje_error(){
        // Ver que datos faltan
        var mensaje_a_enviar = "Faltan los siguientes datos: \n"

        if (editText2.text.toString() == ""){
            mensaje_a_enviar += "- Color\n"
        }
        if (marcaSeleccionada.text.toString() == "Seleccione"){
            mensaje_a_enviar += "- Marca\n"
        }
        if (modeloSeleccionado.text.toString() == "Seleccione"){
            mensaje_a_enviar += "- Modelo\n"
        }
        if (spinner3.getSelectedItem().toString() == "Seleccione"){
            mensaje_a_enviar += "- Estado\n"
        }
        if (editText6.text.toString() == ""){
            mensaje_a_enviar += "- Ubicacion\n"
        }
        if (spinner.getSelectedItem().toString() == "Seleccione"){
            mensaje_a_enviar += "- Revisor\n"
        }

        mensaje_a_enviar += "\n Desea continuar de todas formas?"


        val builder = AlertDialog.Builder(this@MainActivity)

        // Set the alert dialog title
        builder.setTitle("Datos incompletos")

        // Display a message on alert dialog
        builder.setMessage(mensaje_a_enviar)

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("Si"){dialog, which ->
            enviar()
        }


        // Display a negative button on alert dialog
        builder.setNegativeButton("No"){dialog,which ->
            Toast.makeText(applicationContext,"No se ha cargado. Faltan datos.",Toast.LENGTH_SHORT).show()
        }



        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    fun checkear(){
        radioGroup2.clearCheck()
        radioGroup3.clearCheck()
        radioGroup4.clearCheck()
        radioGroup5.clearCheck()
        radioGroup6.clearCheck()

        radioButton7.setChecked(true)
        radioButton4.setChecked(true)
        radioButton10.setChecked(true)
        radioButton14.setChecked(true)
        radioButton19.setChecked(true)

        check_wifi.setChecked(true)
        check_bluetooth.setChecked(true)
        check_parlantet.setChecked(true)
        check_auricular.setChecked(true)
        check_portasim.setChecked(true)
        check_parlanted.setChecked(true)
        check_microfono.setChecked(true)
        check_liberar.setChecked(true)
        check_sensor.setChecked(true)
        check_carcasa.setChecked(true)
        check_pincarga.setChecked(true)
        check_camarad.setChecked(true)
        check_botones.setChecked(true)
        check_tactil.setChecked(true)
        check_camarat.setChecked(true)
    }

    fun descheckear(){
        radioGroup2.clearCheck()
        radioGroup3.clearCheck()
        radioGroup4.clearCheck()
        radioGroup5.clearCheck()
        radioGroup6.clearCheck()

        check_wifi.setChecked(false)
        check_bluetooth.setChecked(false)
        check_parlantet.setChecked(false)
        check_auricular.setChecked(false)
        check_portasim.setChecked(false)
        check_parlanted.setChecked(false)
        check_microfono.setChecked(false)
        check_liberar.setChecked(false)
        check_sensor.setChecked(false)
        check_carcasa.setChecked(false)
        check_pincarga.setChecked(false)
        check_camarad.setChecked(false)
        check_botones.setChecked(false)
        check_tactil.setChecked(false)
        check_camarat.setChecked(false)
    }




}
