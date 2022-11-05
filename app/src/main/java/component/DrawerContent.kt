package component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.app_movil_alerta_terremotos.Rutas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(navController: NavHostController, drawerState: DrawerState) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DrawerHeader()
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = Color.LightGray)
        Spacer(modifier = Modifier.height(10.dp))
        NavigationDrawerItems(navController,drawerState)
        Spacer(modifier = Modifier.weight(1f))




    }

}