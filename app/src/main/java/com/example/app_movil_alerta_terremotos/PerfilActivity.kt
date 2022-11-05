package com.example.app_movil_alerta_terremotos

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var Auth: FirebaseAuth
@Composable
fun FrmPerfil(){
    var TAG: String = "App-Sismos"
    Auth = FirebaseAuth.getInstance()
    val currentUser = Auth.currentUser
    fun agregarusu(Id : String,nombre : String , apellido : String, celular : String){
        val db = Firebase.firestore
        val libro = hashMapOf(
            "ID" to Id,
            "Nombre" to nombre,
            "Apellido" to apellido,
            "Celular" to celular

        )
        db.collection("Persona")
            .add(libro)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Documento agregado con ID: ${documentReference.id}")
            }
            .addOnFailureListener { error ->
                Log.w(TAG, "Error al agregar el documento", error)

            }
    }

    val id = remember { mutableStateOf("") }
    val nombre = remember { mutableStateOf("") }
    val apellido = remember { mutableStateOf("") }
    val celular = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }
    id.value = currentUser?.uid.toString()
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

           Text(text = "Mis Datos")

            Spacer(modifier = Modifier.padding(2.dp))

                OutlinedTextField( modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                    value =  currentUser?.uid.toString(),
                    onValueChange = {
                        id.value = it
                    },
                    label = {
                        Text(text = "ID")

                    }
                )
                Spacer(modifier = Modifier.padding(2.dp))

            OutlinedTextField( modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
                value =  nombre.value,
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
                    value = apellido.value,
                    onValueChange = {
                        apellido.value = it
                    },
                    label = {
                        Text(text = "Ciudad")

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
                value = currentUser?.email.toString(),
                onValueChange = {
                    correo.value = it
                },
                label = {
                    Text(text = "Correo")

                }
            )


            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    agregarusu(  currentUser?.uid.toString() , nombre.value, apellido.value, celular.value)
                },shape = RoundedCornerShape(60)
            ) {
                Text(text = "Guardar")
            }
            Spacer(modifier = Modifier.padding(2.dp))



        }
    }
}