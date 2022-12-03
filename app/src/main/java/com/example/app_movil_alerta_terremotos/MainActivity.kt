package com.example.app_movil_alerta_terremotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_movil_alerta_terremotos.Login.LoginViewModel
import com.example.app_movil_alerta_terremotos.ui.theme.AppMovilAlertaTerremotosTheme
import androidx.compose.runtime.livedata.observeAsState
import com.example.app_movil_alerta_terremotos.Model.LocationViewModel
import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.app_movil_alerta_terremotos.Model.LocationDetails
import com.example.app_movil_alerta_terremotos.Model.Ubicacion
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var Auth: FirebaseAuth
class MainActivity : ComponentActivity() {
    private val locationViewModel : LocationViewModel by viewModels()
    var Ubi = Ubicacion()


    override fun onCreate(savedInstanceState: Bundle?) {
        Auth = FirebaseAuth.getInstance()
        val currentUser = Auth.currentUser
        super.onCreate(savedInstanceState)
        setContent {

            val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)
            val location = locationViewModel.getLocationLiveData().observeAsState()

            AppMovilAlertaTerremotosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                   // color = MaterialTheme.colors.background
                ) {
                    NavegacionHost(loginViewModel = loginViewModel)

                    GPS(location.value)
                    if (currentUser?.uid == null){
                        agregate(location.value)
                    }else{
                        updateubicacion(location.value)
                    }
                }
            }
            prepLocationUpdates()
        }
    }
    private fun prepLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) {
            requestLocationUpdates()
        } else {
            requestSinglePermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private val requestSinglePermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            isGranted ->
        if (isGranted) {
            requestLocationUpdates()
        } else {
            Toast.makeText(this, "GPS Unavailable", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestLocationUpdates() {
        locationViewModel.startLocationUpdates()
    }

}

fun GPS(location: LocationDetails?) {
    location?.let {
        Log.d("coordenadas", "Latitud es: ${location.latitude}")
        Log.d("coordenadas", "Longitud es: ${location.longitude}")

    }
}
fun updateubicacion(location: LocationDetails?){
    Auth = FirebaseAuth.getInstance()
    val currentUser = Auth.currentUser
    val db = Firebase.firestore
    location?.let {
        val up = db.collection("Ubicacion")
        up.whereEqualTo("ID" , "123").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result) {
                    val update: MutableMap<String, Any> = HashMap()
                    update["ID"] = currentUser?.uid.toString()
                    update["Longitud"] = location.longitude
                    update["Latitud"] = location.latitude
                    up.document(document.id).set(update, SetOptions.merge())
                }
            }
        }
    }
}
fun agregate(location: LocationDetails?){
    Auth = FirebaseAuth.getInstance()
    val currentUser = Auth.currentUser
    val db = Firebase.firestore
    location?.let {
    val ubica = hashMapOf(

        "Latitud" to location.latitude,
        "Longitud" to location.longitude,
        "ID" to "123"

    )

    db.collection("Ubicacion")
        .add(ubica)
        .addOnSuccessListener { documents ->
            val up = db.collection("Persona")
            up.whereEqualTo("ID" , currentUser?.uid).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val update: MutableMap<String, Any> = HashMap()
                        update["Idu"] = documents.id
                        up.document(document.id).set(update, SetOptions.merge())
                    }
                }
            }
            Log.d("fubi", "Documento agregado con ID: ${documents.id}")

        }
        .addOnFailureListener { error ->
            Log.w("fubi", "Error al agregar el documento", error)

        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}

