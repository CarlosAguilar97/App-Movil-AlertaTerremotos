package com.example.app_movil_alerta_terremotos

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_movil_alerta_terremotos.Model.LocationViewModel
import component.DrawerContent
import kotlinx.coroutines.launch




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationPage(function: () -> String) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()


    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(navController, drawerState)
        }, drawerState = drawerState
    ) {


        androidx.compose.material3.Scaffold(topBar = {
            SmallTopAppBar(
                title = { androidx.compose.material3.Text(text = "Menu") },
                navigationIcon = {

                    IconButton(onClick = {

                        if (drawerState.isClosed) {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        } else {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        }

                    }) {
                        androidx.compose.material3.Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Drawer Menu."
                        )
                    }

                })
        }) {

            Box(modifier = Modifier.padding(it)) {


                NavHost(navController = navController, startDestination = "PerfilPagina") {

                    composable("PerfilPagina") {
                        FrmPerfil()
                    }

                    composable("ApoderadoPagina") {
                        FrmApoderado()
                    }

                    composable("BotPagina") {
                        FrmBot()
                    }
                    composable("SismosPagina") {

                    }
                    composable("SalirPagina") {

                    }

                }

            }


        }

    }
}




