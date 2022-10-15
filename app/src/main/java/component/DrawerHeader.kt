package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.app_movil_alerta_terremotos.R
import com.google.firebase.auth.FirebaseAuth
private lateinit var Auth: FirebaseAuth
@OptIn(ExperimentalUnitApi::class)
@Composable
fun DrawerHeader() {
    Auth = FirebaseAuth.getInstance()
    val currentUser = Auth.currentUser
    Column(
        modifier = Modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.perfil),
            contentDescription = "profile pic",
            modifier = Modifier
                .clip(CircleShape)
                .width(150.dp)
                .border(width = 2.dp, color = Color.Gray, shape = CircleShape)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = currentUser?.displayName.toString(), fontSize = TextUnit(18F, TextUnitType.Sp), color = Color.Black,)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = currentUser?.email.toString(), fontSize = TextUnit(14F, TextUnitType.Sp), color = Color.Gray)




    }

}