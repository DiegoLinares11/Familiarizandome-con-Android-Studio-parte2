package com.uvg.example.lab2codelab

import android.os.Bundle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uvg.example.lab2codelab.ui.theme.Lab2CodelabTheme
import androidx.compose.material3.Button
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab2CodelabTheme {
                limonadaApp()

            }
        }
    }
}


@Composable
fun prepararLimonada(modifier: Modifier = Modifier){
    var paginaActual by remember { mutableStateOf(1)}
    var vecesExprimida by remember {
        mutableStateOf(0)
    }
    var maxExprimirLimon by remember { mutableStateOf(0)}

    val imageResource = when (paginaActual) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val textResource = when (paginaActual) {
        1 -> R.string.imagenArbol
        2 -> R.string.imagenLimon
        3 -> R.string.imagenLimonada
        4 -> R.string.imagenVasoVacio
        else -> R.string.imagenArbol
    }

    Column (
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(textResource),
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp)) // Forma redondeada alrededor de la imagen
                .background(Color(0xFFE0F7FA)) // Fondo de la imagen con color suave
                .padding(16.dp) // Espaciado interno
                .clickable {
                    when (paginaActual) {
                        1 -> {
                            paginaActual = 2
                            maxExprimirLimon =
                                Random.nextInt(2, 5) // Genera un número aleatorio entre 2 y 4
                            vecesExprimida = 0
                        }

                        2 -> {
                            vecesExprimida++
                            if (vecesExprimida >= maxExprimirLimon) {
                                paginaActual = 3
                            }
                        }

                        3 -> {
                            paginaActual = 4
                        }

                        4 -> {
                            paginaActual = 1
                        }
                    }
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(textResource))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun limonadaApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                    Text(text = "Lemonade")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFFFFEB3B), // Color amarillo
                    titleContentColor = Color.Black // Color del texto
                )
            )
        }
    ) { padding ->
        prepararLimonada(modifier = Modifier.padding(padding))
    }
}
