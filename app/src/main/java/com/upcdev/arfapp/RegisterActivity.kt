package com.upcdev.arfapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnLogRegister.setOnClickListener {
            onBackPressed()
        }
        var dbHelper: DBHelper = DBHelper(baseContext)
        // get reference to all views
        var username = findViewById(R.id.txt_regname) as EditText
        var email = findViewById(R.id.txt_regemail) as EditText
        var password = findViewById(R.id.txt_regpassword) as EditText
        var btn_registro = findViewById(R.id.btnRegistro) as Button

        btn_registro.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()
            val emailreg = email.text.toString()
            if (user == "" || pass == "") Toast.makeText(
                this@RegisterActivity,
                "Ingrese todos los campos",
                Toast.LENGTH_SHORT
            ).show() else {
                val checkuser = dbHelper.checkusername(user)
                if (checkuser == false) {
                    val insert = dbHelper.insertData(user, emailreg, pass)
                    if (insert == true) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registro exitoso",
                            Toast.LENGTH_SHORT
                        ).show()
                        // val intent = Intent(applicationContext, LoginActivity::class.java)
                        // startActivity(intent)
                        username.setText("")
                        email.setText("")
                        password.setText("")

                        overridePendingTransition(R.anim.slide_to_right, R.anim.slide_from_left)
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Fallo en registro",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Usuario ya existe, ingrese!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }
}