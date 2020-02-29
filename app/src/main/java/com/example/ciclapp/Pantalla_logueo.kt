package com.example.ciclapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ciclapp.usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_pantalla_logueo.*

var nombre = ""
class Pantalla_logueo : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_logueo)
        val auth = FirebaseAuth.getInstance()
        version_number.text = BuildConfig.VERSION_NAME

        // Esto hace que cuando ya este logueado, pase a la siguiente pantalla
        if(auth.currentUser?.email.toString() != "null"){
            val progress = ProgressDialog.show(this@Pantalla_logueo, "Iniciando sesion", "Espere un momento", true)

            val database = FirebaseDatabase.getInstance().reference.child("Users").child(auth.currentUser?.uid.toString())
            val valueEventListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    nombre = dataSnapshot.children.elementAt(1).value.toString()

                    progress.dismiss()
                    finish()
                    val intent = Intent(this@Pantalla_logueo, MainActivity::class.java)
                    startActivity(intent)

                }
                override fun onCancelled(databaseError: DatabaseError) {
                    progress.dismiss()
                }
            }
            database.addListenerForSingleValueEvent(valueEventListener)
        }

        // Boton para iniciar sesion
        loguearse.setOnClickListener{
            if(usuario.text.toString() == "" || clave.text.toString() == ""){
                Toast.makeText(this@Pantalla_logueo, "No puede dejar campos vacios", Toast.LENGTH_LONG).show()
            }else if(clave.text.toString().length < 6){
                Toast.makeText(this@Pantalla_logueo, "La contraseña debe ser de 6 caracteres o mas", Toast.LENGTH_LONG).show()
            }else{
                val progress = ProgressDialog.show(this@Pantalla_logueo, "Iniciando sesion", "Espere un momento", true)
                auth.signInWithEmailAndPassword(usuario.text.toString(), clave.text.toString()).addOnCompleteListener{
                    if(it.isSuccessful){
                        val database = FirebaseDatabase.getInstance().reference.child("Users").child(auth.currentUser?.uid.toString())
                        val valueEventListener: ValueEventListener = object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                val habilitado = dataSnapshot.children.elementAt(0).value.toString()
                                nombre = dataSnapshot.children.elementAt(1).value.toString()
                                progress.dismiss()
                                if(habilitado == "True"){
                                    finish()
                                    val intent = Intent(this@Pantalla_logueo, MainActivity::class.java)
                                    startActivity(intent)
                                }else{
                                    Toast.makeText(this@Pantalla_logueo, "Usuario no habilitado", Toast.LENGTH_LONG).show()
                                    auth.signOut()
                                }
                            }
                            override fun onCancelled(databaseError: DatabaseError) {
                                progress.dismiss()
                                Toast.makeText(this@Pantalla_logueo, "No hay conexion", Toast.LENGTH_LONG).show()
                            }
                        }
                        database.addListenerForSingleValueEvent(valueEventListener)
                    } else{
                        Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show()
                        progress.dismiss()
                    }
                }
            }
        }

        // Este es el boton para registrarse
        boton_registrarse.setOnClickListener {
            finish()
            val intent = Intent(this, Pantalla_registracion::class.java)
            startActivity(intent)
        }

        // Boton para recuperar contraseña
        textView3.setOnClickListener{
            auth.sendPasswordResetEmail(usuario.text.toString())
            Toast.makeText(this@Pantalla_logueo, "Se ha enviado un link para restaurar su contraseña a: " + usuario.text.toString(), Toast.LENGTH_LONG).show()
        }
    }



}
