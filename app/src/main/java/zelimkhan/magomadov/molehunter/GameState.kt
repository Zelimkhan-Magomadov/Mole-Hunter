package zelimkhan.magomadov.molehunter

data class GameState(
    val holes: List<List<Boolean>> = List(3) { List(3) { false } },
    val time: Int = 0,
    val points: Int = 0,
    val isGameOver: Boolean = false
)
