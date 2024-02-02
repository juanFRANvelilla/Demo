package com.example.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demo.ui.theme.DemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ToolBarScreen()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBarScreen(){
    val navigation = rememberNavController()
    
    val actionsItems: List<ActionsItems> = listOf(
        ActionsItems("favoritos", R.drawable.icon_asset, "ir a favoritos"),
        ActionsItems("configuracion", R.drawable.ic_launcher_background, "ir a configuracion")
    )
    
    Scaffold (
        topBar = {
            TopAppBar(
                title = { "menu principal" },
                actions = {
                    actionsItems.forEach() {
                        IconButton(onClick = { onIconClick(it.nombre, navigation) }) {
                            Icon(
                                painterResource(id = it.icon),
                                contentDescription = it.contentDesc
                            )

                        }
                    }
                }
            )
        }
    ){
        NavHost(
            navController = navigation,
            startDestination = Routes.PantallaFavoritos.route
        ){
            composable(Routes.PantallaFavoritos.route){ PantallaFavoritos() }
            composable(Routes.PantallaConfiguracion.route){ PantallaConfiguracion() }

        }
    }
}

fun onIconClick(idPantalla: String, navController: NavController){
    when(idPantalla){
        "favoritos" -> { navController.navigate(Routes.PantallaFavoritos.route) }
        "configuracion" -> { navController.navigate(Routes.PantallaConfiguracion.route) }
    }
}





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("") }
    var show by remember { mutableStateOf("") }
    var textVisible by remember { mutableStateOf(false) }

    Column(){
        TextField(
            value = message,
            onValueChange = {x->
                message = x
                textVisible = false
            }
        )

        Button(
            modifier = Modifier
                .background(color = Color.Red),
            onClick = {
                textVisible = true
                show = message

            },
        ) {
            Text("Send")
        }

        if (textVisible) {
            Text(
                text = "Hello $show!"
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoTheme {
        Greeting("Android")
    }
}