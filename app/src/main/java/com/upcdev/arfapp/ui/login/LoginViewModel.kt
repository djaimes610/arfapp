package com.upcdev.arfapp.ui.login

import android.content.Context
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.map
import com.upcdev.arfapp.FirebaseUserLiveData
import com.upcdev.arfapp.data.LoginRepository
import com.upcdev.arfapp.data.Result

import com.upcdev.arfapp.R
import kotlin.random.Random

class LoginViewModel : ViewModel() {

    companion object {
        val androidFacts = arrayOf(
            "Los perros no son todo en nuestra vida pero ellos la hacen completa.",
            "No hay nada más verdadero en este mundo que el amor de un buen perro.",
            "Un perro puede expresar más con su cola en minutos que lo que un dueño puede expresar con su lengua en horas.",
            "El amor es la emoción que una mujer siente siempre por un perro caniche y a veces por un hombre."
        )
    }

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

    /**
     * Gets a fact to display based on the user's set preference of which type of fact they want
     * to see (Android fact or California fact). If there is no logged in user or if the user has
     * not set a preference, defaults to showing Android facts.
     */
    fun getFactToDisplay(context: Context): String {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val factTypePreferenceKey = context.getString(R.string.preference_fact_type_key)
        val defaultFactType = context.resources.getStringArray(R.array.fact_type)[0]
        val funFactType = sharedPreferences.getString(factTypePreferenceKey, defaultFactType)

        return androidFacts[Random.nextInt(0, androidFacts.size)]
    }
}