package com.upcdev.arfapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistroEventoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistroEventoFragment : Fragment() {

    internal var output: File? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_registro_evento, container, false)
        var animalDescription = view.findViewById(R.id.et_descAnimal) as EditText
        var animalLocation = view.findViewById(R.id.et_Location) as EditText
        var btnRegAnimal = view.findViewById(R.id.btnGuardarEvento) as Button
        var dbHelper = DBHelper(requireContext())
        btnRegAnimal.setOnClickListener {
            val aDesc = animalDescription.text.toString()
            val aLoc = animalLocation.text.toString()

            if (aDesc == "" || aLoc == "") Toast.makeText(
                requireContext(),
                "Ingrese todos los campos",
                Toast.LENGTH_SHORT
            ).show() else {
                    val insert = dbHelper.insertLostAnimal(aDesc, aLoc, "")
                    if (insert) {
                        Toast.makeText(
                            requireContext(),
                            "Registro exitoso",
                            Toast.LENGTH_SHORT
                        ).show()
                        // val intent = Intent(applicationContext, LoginActivity::class.java)
                        // startActivity(intent)
                        animalDescription.setText("")
                        animalLocation.setText("")
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Fallo en registro",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        return view
    }


}