class Game {
    private val gameField = arrayListOf(
        arrayListOf(Gamer.Nothing, Gamer.Nothing, Gamer.Nothing),
        arrayListOf(Gamer.Nothing, Gamer.Nothing, Gamer.Nothing),
        arrayListOf(Gamer.Nothing, Gamer.Nothing, Gamer.Nothing)
    )
    private var currentGamer = Gamer.Krestik
    private var winner: Gamer? = null
    private var currSteps = 0

    fun makeStepOnBoard(x: Int, y: Int): Boolean {
        if (x >= gameField.size || y >= gameField.size || gameField[x][y] !== Gamer.Nothing) {
            return false
        }
        gameField[x][y] = currentGamer
        if (maxOf(sumLine(x, y, 0, 1), sumLine(x, y, 1, 0), sumLine(x, y, 1, 1), sumLine(x, y, 1, -1)) == 3) {
            winner = currentGamer
        }
        currentGamer = if (currentGamer === Gamer.Krestik) Gamer.Kruzhochek else Gamer.Krestik
        if (++currSteps >= 9 && winner == null) {
            winner = Gamer.Nothing
        }
        return true
    }

    val isGameStarted
        get() = winner == null

    override fun toString(): String {
        val builder = StringBuilder()
        gameField.forEach { lines ->
            builder.append("|")
            lines.forEach { field ->
                builder.append(field.field)
                builder.append('|')
            }
            builder.append('\n')
        }
        if (winner == null) {
            builder.append("Текущий игрок - ")
            builder.append(currentGamer.fieldName)
        } else {
            builder.append(if (winner === Gamer.Nothing) "Нет победителя" else "Победитель - " + winner!!.fieldName)
        }
        return builder.toString()
    }

    private fun sumLine(startX: Int, startY: Int, dx: Int, dy: Int): Int {
        val gamer = gameField[startX][startY]
        var sum = -1
        var x = startX
        var y = startY
        while (x >= 0 && y >= 0 && x < gameField.size && y < gameField.size && gameField[x][y] === gamer) {
            ++sum
            x += dx
            y += dy
        }
        x = startX
        y = startY
        while (x >= 0 && y >= 0 && x < gameField.size && y < gameField.size && gameField[x][y] === gamer) {
            ++sum
            x -= dx
            y -= dy
        }
        return sum
    }
}
