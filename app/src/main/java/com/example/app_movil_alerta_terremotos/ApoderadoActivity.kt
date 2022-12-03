package com.example.app_movil_alerta_terremotos

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.app_movil_alerta_terremotos.Model.Apoderado
import com.example.app_movil_alerta_terremotos.Model.Persona
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
private lateinit var Auth: FirebaseAuth
@Composable
fun FrmApoderado(){
    val nombre = remember { mutableStateOf("") }
    val parentesco = remember { mutableStateOf("") }
    val celular = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }
    val idocc = remember { mutableStateOf("") }
    var Person = Persona()
    var Apo = Apoderado()
    val db = Firebase.firestore
    var TAG: String = "App-Sismos"
    var apoder = Apoderado()
    Auth = FirebaseAuth.getInstance()
    val currentUser = Auth.currentUser
    db.collection("Persona")
        .whereEqualTo("ID", currentUser?.uid.toString())
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                Person = document.toObject(Persona::class.java)
                //igualamos datos de firebase con los de nuestra clase
                idocc.value = Person.Idoc.toString()
                //Consola
                Log.d(TAG, Person.Nombre.toString())
                Log.d(TAG, Person.Celular.toString())
            }
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }
    fun añadiriduc(idoc : String){
        val up = db.collection("Persona")
        up.whereEqualTo("ID" , currentUser?.uid).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result) {
                    Apo = document.toObject(Apoderado::class.java)
                    val update: MutableMap<String, Any> = HashMap()
                    update["Ida"] = idoc
                    up.document(document.id).set(update, SetOptions.merge())
                }
            }
        }
    }
    fun agregarapo(correo : String,nombre : String , parentesco : String, celular : String){


        val libro = hashMapOf(
            "ID" to currentUser?.uid.toString(),
            "Nombre" to nombre,
            "Parentesco" to parentesco,
            "Celular" to celular,
            "Correo" to correo,

        )
        db.collection("Apoderado")
            .add(libro)
            .addOnSuccessListener { documentReference ->
                añadiriduc(documentReference.id)
                Log.d(TAG, "Documento agregado con ID: ${documentReference.id}")
            }
            .addOnFailureListener { error ->
                Log.w(TAG, "Error al agregar el documento", error)

            }
    }
    db.collection("Apoderado")
        .whereEqualTo("ID", currentUser?.uid.toString())
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                apoder = document.toObject(Apoderado::class.java)
                //igualamos datos de firebase con los de nuestra clase
                nombre.value = apoder.Nombre.toString()
                celular.value = apoder.Celular.toString()
                correo.value = apoder.Correo.toString()
                parentesco.value = apoder.Parentesco.toString()
                //Consola
                Log.d(TAG, apoder.Nombre.toString())
                Log.d(TAG, apoder.Celular.toString())
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
        Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
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
        Modifier
            .padding(50.dp)
            .fillMaxWidth()
            .padding(top = 120.dp)
    ) {


        Column(
            Modifier.fillMaxSize()
            ,verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Datos de Apoderado")
            Spacer(modifier = Modifier.padding(2.dp))

            Spacer(modifier = Modifier.padding(2.dp))
            OutlinedTextField( modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
                value = nombre.value,
                onValueChange = {
                   nombre.value = it
                },
                label = {
                    Text(text = "Nombre")

                }
            )
            Spacer(modifier = Modifier.padding(2.dp))
            OutlinedTextField( modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
                value = parentesco.value,
                onValueChange = {
                    parentesco.value = it
                },
                label = {
                    Text(text = "Parentesco")

                }
            )
            Spacer(modifier = Modifier.padding(2.dp))
            OutlinedTextField( modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
                value = celular.value,
                onValueChange = {
                    celular.value = it
                },
                label = {
                    Text(text = "Celular")

                }
            )
            Spacer(modifier = Modifier.padding(2.dp))
            OutlinedTextField( modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
                value = correo.value,
                onValueChange = {
                    correo.value = it
                },
                label = {
                    Text(text = "Correo")

                }
            )
            Spacer(modifier = Modifier.padding(2.dp))



            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    agregarapo(correo.value,nombre.value, parentesco.value, celular.value)
                },shape = RoundedCornerShape(60)
            ) {
                Text(text = "Agregar")
            }
            Spacer(modifier = Modifier.padding(2.dp))



        }
    }
}