package com.example.app_movil_alerta_terremotos

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun FrmBot(){
    var TAG: String = "App-Sismos"
    fun estadobot(Id : String, estado : Boolean){
        val db = Firebase.firestore
        val libro = hashMapOf(
            "ID" to Id,
            "Estado" to estado,

        )
        db.collection("Bot")
            .add(libro)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Documento agregado con ID: ${documentReference.id}")
            }
            .addOnFailureListener { error ->
                Log.w(TAG, "Error al agregar el documento", error)

            }
    }
    var res = remember { mutableStateOf(false) }//1

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
                            onClick = { res.value = true } ,shape = RoundedCornerShape(60)
                            ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)) {
                            Text(text = "On")
                            //estadobot(id.value, estado = true)
                        }
                        Button( onClick = {
                            res.value = false
                        }

                            ,shape = RoundedCornerShape(60)) {
                            Text(text = "Off",color = Color.White)
                        }
                    }else
                    {
                        Button(
                            onClick = { res.value = true } ,shape = RoundedCornerShape(60)
                            ) {
                            Text(text = "On")

                        }
                        Button( onClick = {
                            res.value = false
                        }
                            ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                            ,shape = RoundedCornerShape(60)) {
                            Text(text = "Off",color = Color.White)
                        }
                    }



            }
            Text(text = "Esta funcion puede activarse de 2 maneras \n" +
                    "Manual : activarlo desde el switch\n" +
                    " automatica : cuando detecte un sismo \n" +
                    "de grado terremoto.")
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "El bot mandara en tiempo real la ubicacion\n" +
                    "de este dispositivo al apoderado \n" +
                    "hasta que la desactiven.")
        }
    }
}