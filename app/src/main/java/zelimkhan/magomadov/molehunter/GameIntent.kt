package zelimkhan.magomadov.molehunter

sealed interface GameIntent {
    data class WhackMole(val row: Int, val column: Int) : GameIntent
}
