package component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.example.app_movil_alerta_terremotos.*
import com.example.app_movil_alerta_terremotos.Login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

private lateinit var Auth: FirebaseAuth
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerItems(
    navController: NavHostController,
    drawerState: DrawerState) {
    val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current
    val act = remember { mutableStateOf(false) }
    var scope = rememberCoroutineScope()

    var currentBackStackEntryAsState = navController.currentBackStackEntryAsState()

    var destination = currentBackStackEntryAsState.value?.destination



    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.AddAPhoto, contentDescription = "Perfil") },
        label = { Text(text = "Perfil") },
        selected = destination?.route == "PerfilPagina",
        onClick = {
            navController.navigate("PerfilPagina", navOptions {
                this.launchSingleTop = true
                this.restoreState = true

            })
            scope.launch {
                drawerState.close()
            }

        }, modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding).width(300.dp)
    )

    Spacer(modifier = Modifier.height(5.dp))
    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.PersonAdd, "Apoderado") },
        label = { Text(text = "Apoderado") },
        selected = destination?.route == "ApoderadoPagina",

        onClick = {
            navController.navigate("ApoderadoPagina", navOptions {
                this.launchSingleTop = true
                this.restoreState = true

            })
            scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding).width(300.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))


    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.Settings, "Bot") },

        label = { Text(text = "Bot") },
        selected = destination?.route == "BotPagina",
        onClick = {
            navController.navigate("BotPagina", navOptions {
                this.launchSingleTop = true
                this.restoreState = true
            })
            scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding).width(300.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))
    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.Bluetooth, "Sismos") },

        label = { Text(text = "Sismos") },
        selected = destination?.route == "SismosPagina",
        onClick = {
            navController.navigate("SismosPagina", navOptions {
                this.launchSingleTop = true
                this.restoreState = true
            })
            scope.launch {
                drawerState.close()
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding).width(300.dp)
    )
    Spacer(modifier = Modifier.height(5.dp))
    NavigationDrawerItem(
        icon = { Icon(Icons.Filled.ExitToApp, "Salir") },

        label = { Text(text = "Cerrar Sesión") },
        selected = destination?.route == "SalirPagina",
        onClick = {

            scope.launch {
                Firebase.auth.signOut()
                drawerState.close()
                act.value = true
            }
        },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding).width(300.dp)
    )
    if (act.value == true) {
        navController.navigate(Rutas.inicio.ruta)
    }
}
