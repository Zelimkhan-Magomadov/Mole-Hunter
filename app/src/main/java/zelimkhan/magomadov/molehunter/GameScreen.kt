package zelimkhan.magomadov.molehunter

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import zelimkhan.magomadov.molehunter.ui.theme.MoleHunterTheme

@Destination
@Composable
fun GameScreen(
    navigator: DestinationsNavigator = EmptyDestinationsNavigator,
) {
    val viewModel: GameViewModel = viewModel()
    val gameState: GameState = viewModel.gameState.collectAsState().value
    val onIntent: (GameIntent) -> Unit = viewModel::onIntent

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
            text = gameState.time.toString(),
            fontSize = 50.sp
        )

        Spacer(modifier = Modifier.height(64.dp))

        MoleHoles(
            gameState.holes,
            onIntent = onIntent
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(id = R.string.points),
            fontSize = 18.sp
        )

        Text(
            text = gameState.points.toString(),
            fontSize = 40.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.grass),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
private fun MoleHoles(
    holes: List<List<Boolean>>,
    onIntent: (GameIntent) -> Unit
) {
    Log.d("Mole", holes.toString())
    Column(
        modifier = Modifier.fillMaxHeight(0.6f),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        for ((rowIndex, row) in holes.withIndex()) {
            Log.d("Mole", "Row: $rowIndex")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for ((columnIndex, hasMole) in row.withIndex()) {
                    MoleHole(hasMole) {
                        onIntent(GameIntent.WhackMole(row = rowIndex, column = columnIndex))
                    }
                    Log.d("Mole", "Column: $columnIndex")
                }
            }
        }
    }
}

@Composable
private fun MoleHole(
    hasMole: Boolean = false,
    onMoleHit: () -> Unit
) {
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
                    .offset(x = 0.dp, y = (-40).dp)
                    .clickable { onMoleHit() },
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