package com.example.ciclapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ciclapp.nombre
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_pantalla_registracion.*

class Pantalla_registracion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registracion)

        val database = FirebaseDatabase.getInstance()
        val auth = FirebaseAuth.getInstance()

        val dbReference = database.reference.child("Users")

        // Este boton cancela
        boton_cancelar.setOnClickListener {
            finish()
            val intent = Intent(this, Pantalla_logueo::class.java)
            startActivity(intent)
        }

        // Este boton registra y manda el nombre tambien
        Registracion.setOnClickListener {
            if(nombre.text.toString() == "" || email.text.toString() == "" || clave_usuario.text.toString() == ""){
                Toast.makeText(this, "No puede dejar campos vacios", Toast.LENGTH_LONG).show()
            }else if(clave_usuario.text.toString().length < 6){
                Toast.makeText(this, "La contraseña debe ser de 6 caracteres o mas", Toast.LENGTH_LONG).show()
            }else{
                auth.createUserWithEmailAndPassword(email.text.toString(), clave_usuario.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(this, "Registro exitoso. Esperando confirmacion", Toast.LENGTH_LONG).show()

                        val userBD=dbReference.child(auth.currentUser?.uid.toString())
                        userBD.child("Name").setValue(nombre.text.toString())
                        userBD.child("Habilitado").setValue("False")

                        auth.signOut()
                        finish()
                        val intent = Intent(this, Pantalla_logueo::class.java)
                        startActivity(intent)
                    } else{
                        Toast.makeText(this, "Registro fallido", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


}
