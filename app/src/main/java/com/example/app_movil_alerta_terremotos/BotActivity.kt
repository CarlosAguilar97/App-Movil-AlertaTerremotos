package com.example.app_movil_alerta_terremotos

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.app_movil_alerta_terremotos.Model.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import androidx.activity.viewModels

private lateinit var Auth: FirebaseAuth
var Ubi = Ubicacion()
@Composable
fun FrmBot(){
    var TAG: String = "App-SismosBot"
    val res = remember { mutableStateOf(false) }//1
    var Person = Persona()
    var bot = Bot()
    val idbot = remember { mutableStateOf("") }
    val idocc = remember { mutableStateOf("") }
    val long = remember { mutableStateOf("") }
    val lat = remember { mutableStateOf("") }
    Auth = FirebaseAuth.getInstance()
    val currentUser = Auth.currentUser
    val db = Firebase.firestore
    fun obtener(idc : String){
        val up = db.collection("Ubicacion")
        up.whereEqualTo("ID" , currentUser?.uid).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result) {
                    val update: MutableMap<String, Any> = HashMap()
                    update["Idoc"] = idc
                    up.document(document.id).set(update, SetOptions.merge())
                }
            }
        }
    }

    db.collection("Persona")
        .whereEqualTo("ID", currentUser?.uid.toString())
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                Person = document.toObject(Persona::class.java)
                //igualamos datos de firebase con los de nuestra clase
                obtener(Person.Idoc.toString())
                idocc.value = Person.Idoc.toString()

                //Consola
                Log.d(TAG, Person.Nombre.toString())
                Log.d(TAG, Person.Celular.toString())
            }
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }
    db.collection("Bot")
        .whereEqualTo("ID", currentUser?.uid.toString())
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                bot = document.toObject(Bot::class.java)
                //igualamos datos de firebase con los de nuestra clase
                res.value = bot.Estado as Boolean
                //Consola
                idbot.value = bot.ID.toString()
                Log.d(TAG, "ID" + idbot.value)

            }
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }

    fun estadobot(id : String, estado : Boolean){


        val up = db.collection("Bot")
        up.whereEqualTo("ID" , id).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        val update: MutableMap<String, Any> = HashMap()
                        update["Estado"] = estado
                        up.document(document.id).set(update, SetOptions.merge())
                    }
                }
            }

    }
    db.collection("Ubicacion")
        .whereEqualTo("ID", currentUser?.uid.toString())
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                Ubi = document.toObject(Ubicacion::class.java)

                //igualamos datos de firebase con los de nuestra clase
                long.value = Ubi.Longitud.toString()
                lat.value = Ubi.Latitud.toString()
                //Consola
                Log.d(TAG, Person.Nombre.toString())
                Log.d(TAG, Person.Celular.toString())
            }
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }





    Box() {
        Image(
            painter = painterResource(id = R.drawable.backfront),
            contentDescription = null, modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
    Box(
        Modifier.fillMaxWidth().padding( top = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(

            painter = painterResource(id = R.drawable.perfil),
            contentDescription = "profile pic",
            modifier = Modifier
                .clip(CircleShape)
                .width(120.dp)
                .border(width = 2.dp, color = Color.White, shape = CircleShape)
        )
    }
    Box(
        Modifier.padding(50.dp)
            .fillMaxWidth().padding( top = 120.dp)
    ) {


        Column(
            Modifier.fillMaxSize()
            ,verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = "Control de Bot")
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                Modifier
                    .height(39.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(60))
            ) {
                    if (res.value.equals(false)){
                        Button(
                            onClick = {
                                res.value = true
                                if (idbot.value.isEmpty()){
                                    val bot = hashMapOf(
                                        "ID" to currentUser?.uid,
                                        "Estado" to res.value,
                                        "Idoc" to idocc.value,
                                    )
                                    db.collection("Bot")
                                        .add(bot)
                                        .addOnSuccessListener { documentReference ->
                                            Log.d(TAG, "Documento agregado con ID: ${documentReference.id}")
                                        }
                                        .addOnFailureListener { error ->
                                            Log.w(TAG, "Error al agregar el documento", error)

                                        }
                                }else {
                                    estadobot(currentUser?.uid.toString(), res.value)
                                }
                                      } ,shape = RoundedCornerShape(60)
                            ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)) {
                            Text(text = "On")

                        }
                        Button( onClick = {
                            res.value = false
                            if (idbot.value.isEmpty()){
                                val bot = hashMapOf(
                                    "ID" to currentUser?.uid,
                                    "Estado" to res.value,
                                    "Idoc" to idocc.value,
                                )
                                db.collection("Bot")
                                    .add(bot)
                                    .addOnSuccessListener { documentReference ->
                                        Log.d(TAG, "Documento agregado con ID: ${documentReference.id}")
                                    }
                                    .addOnFailureListener { error ->
                                        Log.w(TAG, "Error al agregar el documento", error)

                                    }
                            }else {
                                estadobot(currentUser?.uid.toString(), res.value)
                            }
                        }

                            ,shape = RoundedCornerShape(60)) {
                            Text(text = "Off",color = Color.White)
                        }
                    }else
                    {
                        Button(
                            onClick = {
                                res.value = true
                                if (idbot.value.isEmpty()){
                                    val bot = hashMapOf(
                                        "ID" to currentUser?.uid,
                                        "Estado" to res.value,
                                        "Idoc" to idocc.value,
                                    )
                                    db.collection("Bot")
                                        .add(bot)
                                        .addOnSuccessListener { documentReference ->
                                            Log.d(TAG, "Documento agregado con ID: ${documentReference.id}")
                                        }
                                        .addOnFailureListener { error ->
                                            Log.w(TAG, "Error al agregar el documento", error)

                                        }
                                }else {
                                    estadobot(currentUser?.uid.toString(), res.value)
                                }

                                      } ,shape = RoundedCornerShape(60)
                            ) {
                            Text(text = "On")

                        }
                        Button( onClick = {
                            res.value = false
                            if (idbot.value.isEmpty()){
                                val bot = hashMapOf(
                                    "ID" to currentUser?.uid,
                                    "Estado" to res.value,
                                    "Idoc" to idocc.value,
                                )
                                db.collection("Bot")
                                    .add(bot)
                                    .addOnSuccessListener { documentReference ->
                                        Log.d(TAG, "Documento agregado con ID: ${documentReference.id}")
                                    }
                                    .addOnFailureListener { error ->
                                        Log.w(TAG, "Error al agregar el documento", error)

                                    }
                            }else {
                                estadobot(currentUser?.uid.toString(), res.value)
                            }
                        }
                            ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                            ,shape = RoundedCornerShape(60)) {
                            Text(text = "Off",color = Color.White)
                        }
                    }



            }

            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "Esta funcion puede activarse de 2 maneras \n" +
                    "Manual : activarlo desde el switch\n" +
                    " automatica : cuando detecte un sismo \n" +
                    "de magnitud terremoto.")
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "El bot mandara en tiempo real la ubicacion\n" +
                    "de este dispositivo al apoderado \n" +
                    "hasta que la desactiven.")
        }
    }


}
