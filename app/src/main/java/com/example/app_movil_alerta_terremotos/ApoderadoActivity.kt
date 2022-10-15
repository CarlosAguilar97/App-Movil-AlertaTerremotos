package com.example.app_movil_alerta_terremotos

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

@Composable
fun FrmApoderado(){
    val nombre = remember { mutableStateOf("") }
    val apellido = remember { mutableStateOf("") }
    val celular = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }

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
                .height(55.dp),
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
                .height(55.dp),
                value = apellido.value,
                onValueChange = {
                    apellido.value = it
                },
                label = {
                    Text(text = "Apellido")

                }
            )
            Spacer(modifier = Modifier.padding(2.dp))
            OutlinedTextField( modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
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
                .height(55.dp),
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

                },shape = RoundedCornerShape(60)
            ) {
                Text(text = "Agregar")
            }
            Spacer(modifier = Modifier.padding(2.dp))



        }
    }
}