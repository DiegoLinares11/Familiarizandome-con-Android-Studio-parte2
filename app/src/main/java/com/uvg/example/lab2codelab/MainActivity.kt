package com.uvg.example.lab2codelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uvg.example.lab2codelab.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    Scaffold(
        topBar = { LemonTopBar() },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                LemonadeContent()
            }
        }
    )
}

@Composable
fun LemonadeContent(){
    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    // Mueve la generación aleatoria de umbral dentro de `remember`
    val lemonSqueezeThreshold = remember { (2..4).random() }

    // Configura la imagen y el texto según el estado actual
    val imageRes = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val contentDescription = when (step) {
        1 -> stringResource(id = R.string.lemonTree)
        2 -> stringResource(id = R.string.Lemon)
        3 -> stringResource(id = R.string.GlassLemonade)
        4 -> stringResource(id = R.string.EmptyGlass)
        else -> stringResource(id = R.string.EmptyGlass)
    }

    val instructionText = when (step) {
        1 -> stringResource(id = R.string.imagenArbol)
        2 -> stringResource(id = R.string.imagenLimon)
        3 -> stringResource(id = R.string.imagenLimonada)
        4 -> stringResource(id = R.string.imagenVasoVacio)
        else -> stringResource(id = R.string.imagenVasoVacio)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Cuadro celeste con la imagen adentro
        Box(
            modifier = Modifier
                .size(250.dp) // Tamaño del cuadro
                .background(color = Color(0xFFE0F7FA)) // Fondo celeste
                .padding(16.dp) // Espacio interno dentro del cuadro
                .clip(RoundedCornerShape(12.dp))// Redondeado de las esquinas del cuadro celeste
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(200.dp) // Tamaño de la imagen
                    .clickable {
                        when (step) {
                            1 -> {
                                step = 2 // Avanza al paso 2
                                squeezeCount = 0 // Resetea el conteo de apretadas
                            }
                            2 -> {
                                squeezeCount++
                                if (squeezeCount >= lemonSqueezeThreshold) {
                                    step = 3 // Avanza al paso 3 cuando se alcanza el umbral
                                }
                            }
                            3 -> step = 4 // Avanza al paso 4
                            4 -> step = 1 // Reinicia el ciclo
                        }
                    }
                    .align(Alignment.Center) // Centra la imagen dentro del cuadro
            )
        }

        // Texto de instrucciones
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = instructionText)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonTopBar() {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFFEB3B),
            titleContentColor = Color.Black
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview(){
    LemonadeTheme {
        LemonApp()
    }
}
