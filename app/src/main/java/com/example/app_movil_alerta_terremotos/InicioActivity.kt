package com.example.app_movil_alerta_terremotos

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp




@Composable
fun FrmInicio(NavegarLogin: () -> Unit,NavegarRegistro:() -> Unit){
    //val dataStore = StoreUserEmail(Context)
    //var mail = dataStore.getEmail.collectAsState(initial = "")
    Column(
    ) {

        Box(

        ) {
            Image(
                painter = painterResource(id = R.drawable.backinicio),
                contentDescription = null, modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Box(
                Modifier.fillMaxWidth().padding( top = 100.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(

                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "", modifier = Modifier.size(150.dp)
                )
            }
            Column(
                Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Button(onClick = {
                    NavegarLogin()
                                 },Modifier.width(200.dp),shape = RoundedCornerShape(60),content={
                    Text(text = "Iniciar Sesión", color = Color.White)
                })
                Spacer(modifier = Modifier.padding(3.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Crear Cuenta?",
                        color = Color.White,
                    )
                    Text(
                        text = "Registrese.",
                        color = Color.Red,
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    NavegarRegistro()
                                }
                            )
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }

        }
    }


}


