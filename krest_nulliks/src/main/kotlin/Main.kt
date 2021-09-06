@ExperimentalUnsignedTypes
fun main() {
    val game = Game()
    while (game.isGameStarted) {
        println(game)
        if (!game.makeStepOnBoard(getInt() - 1, getInt() - 1)) {
            println("Неверные координаты поля")
        }
    }
    println(game)
}

@ExperimentalUnsignedTypes
private fun getInt(): Int {
    val warningMessage = "Warning (runtime error): wrong int number (negative or not even int)"
    var currLine = readLine()!!.toUIntOrNull()
    while (currLine == null || currLine == 0U) {
        println(warningMessage)
        currLine = readLine()!!.toUIntOrNull()
    }
    return currLine.toInt()
}
