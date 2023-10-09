package zelimkhan.magomadov.molehunter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zelimkhan.magomadov.molehunter.ui.theme.MoleHunterTheme

@Composable
fun GameScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.time),
            fontSize = 22.sp
        )
        Text(
            text = "29",
            fontSize = 50.sp
        )

        Spacer(modifier = Modifier.height(64.dp))

        Column(
            modifier = Modifier.fillMaxHeight(0.6f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            for (row in 0 until 3) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (column in 0 until 3) {
                        MoleHole()
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(id = R.string.points),
            fontSize = 18.sp
        )

        Text(
            text = "16",
            fontSize = 40.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.grass),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun MoleHole(hasMole: Boolean = false) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(54.dp)
                .background(
                    brush = Brush.radialGradient(
                        listOf(
                            Color(0xFF000000),
                            Color(0xFF673AB7)
                        ),
                        radius = 60f,
                    ),
                    shape = CircleShape
                )
        )
        if (hasMole) {
            Image(
                modifier = Modifier
                    .width(80.dp)
                    .offset(x = 0.dp, y = (-40).dp),
                painter = painterResource(id = R.drawable.mole),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MoleHunterTheme {
        GameScreen()
    }
}