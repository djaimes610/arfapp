package com.upcdev.arfapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnRegLogin.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }
        var dbHelper = DBHelper(baseContext)
        // get reference to all views
        var email = findViewById<EditText>(R.id.txt_email)
        var password = findViewById<EditText>(R.id.txt_password)
        var btnLogin = findViewById<Button>(R.id.btnLogin)

        // set on-click listener
        btnLogin.setOnClickListener {
            val user_name = email.text
            val password = password.text
            Log.i("LOGIN", "Ingresó al método login()")
            Toast.makeText(applicationContext,"Hola",Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Hi there! This is a Toast.", Toast.LENGTH_LONG).show()
            if (user_name.equals("") || password.equals("")) Toast.makeText(
                this@LoginActivity,
                "Please enter all the fields",
                Toast.LENGTH_SHORT
            ).show() else {
                val checkuserpass = dbHelper.checkusernamepassword(user_name.toString(), password.toString())
                if (checkuserpass == true) {
                    Toast.makeText(this@LoginActivity, "Sign in successfull", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            // your code to validate the user_name and password combination
            // and verify the same
        }
    }
}