package zelimkhan.magomadov.molehunter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

private const val GAME_TIME = 30
private const val MOLE_APPEARANCE_INTERVAL = 1000L

class GameViewModel : ViewModel() {
    private val _gameState = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()

    fun onIntent(intent: GameIntent) {
        when (intent) {
            is GameIntent.WhackMole -> whackMole(intent.row, intent.column)
        }
    }

    init {
        viewModelScope.launch {
            for (timeRemaining in GAME_TIME downTo 0) {
                _gameState.update { it.copy(time = timeRemaining) }
                delay(1000)
            }
            _gameState.update { it.copy(isGameOver = true) }
        }

        viewModelScope.launch {
            while (!gameState.value.isGameOver) {
                val position = randomMolePosition()
                _gameState.update {
                    it.copy(
                        holes = changeHoleState(
                            row = position.first,
                            column = position.second,
                        )
                    )
                }
                delay(MOLE_APPEARANCE_INTERVAL)
                val holes = gameState.value.holes
                _gameState.update {
                    it.copy(holes = List(holes.count()) {
                        List(
                            holes.first().count()
                        ) { false }
                    })
                }
            }
        }
    }

    private fun changeHoleState(row: Int, column: Int): List<List<Boolean>> {
        val updatedHoles = gameState.value.holes.map { it.toMutableList() }.toMutableList()
        updatedHoles[row][column] = true
        return updatedHoles
    }

    private fun randomMolePosition(): Pair<Int, Int> {
        val holesRows = gameState.value.holes.count()
        val holesColumns = gameState.value.holes.first().count()

        return Pair(
            first = Random.nextInt(holesRows),
            second = Random.nextInt(holesColumns)
        )
    }

    private fun whackMole(row: Int, column: Int) {
        val holes = gameState.value.holes
        if (holes[row][column]) {
            _gameState.update {
                it.copy(
                    holes = List(holes.count()) { List(holes.first().count()) { false } },
                    points = it.points + 1
                )
            }
        }
    }
}