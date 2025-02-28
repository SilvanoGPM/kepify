package br.com.silvanogpm.kepify.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.silvanogpm.kepify.ui.theme.Green300
import br.com.silvanogpm.kepify.ui.theme.Green500
import br.com.silvanogpm.kepify.ui.theme.GreenAccent100
import br.com.silvanogpm.kepify.ui.theme.GreenAccent200
import br.com.silvanogpm.kepify.ui.theme.GreenAccent700
import br.com.silvanogpm.kepify.ui.theme.Typography

@Composable
fun HomeHero(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .offset(y = -(32).dp)
            .background(
                brush = Brush.linearGradient(listOf(GreenAccent700, Green500, Green300,))
            )
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box {
            Icon(
                imageVector = Icons.Default.Map,
                contentDescription = "Mapa",
                tint = GreenAccent100,
                modifier = Modifier.size(96.dp)
            )

            Icon(
                imageVector = Icons.Default.PinDrop,
                contentDescription = "Marcação no mapa",
                tint = GreenAccent200,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = -(8).dp)
                    .size(48.dp),
            )

        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Kepify",
            style = Typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )

        Text(
            text = "Consulte detalhes sobre endereços de forma prática e eficiente",
            style = Typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}