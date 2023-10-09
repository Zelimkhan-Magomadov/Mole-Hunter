package zelimkhan.magomadov.molehunter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import zelimkhan.magomadov.molehunter.destinations.GameScreenDestination
import zelimkhan.magomadov.molehunter.ui.theme.MoleHunterTheme

@Destination(start = true)
@Composable
fun StartScreen(
    destination: DestinationsNavigator = EmptyDestinationsNavigator
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    listOf(Color(0XFF56AB2F), Color(0XFFA8E063)),
                    radius = 1000f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.record))
            Text(text = "100")
        }

        Button(
            modifier = Modifier
                .align(Alignment.Center)
                .width(200.dp)
                .height(64.dp),
            onClick = {
                destination.navigate(GameScreenDestination)
            },
            border = BorderStroke(
                width = 2.dp,
                color = Color(0xFF08DF04)
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = stringResource(id = R.string.play),
                color = Color.Black,
                fontSize = 36.sp
            )
        }

        Image(
            modifier = Modifier
                .padding(8.dp)
                .width(127.dp)
                .align(Alignment.BottomStart),
            painter = painterResource(id = R.drawable.mole),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MoleHunterTheme {
        StartScreen()
    }
}